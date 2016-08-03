package com.gy.utils.file;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by sam_gan on 2016/6/24.
 *
 */
public class FileUtils {

    public static long getFileSize (String path) {
        return getFileSize(new File(path));
    }

    /**
     * 文件大小，可计算整个文件夹下所有文件大小
     */
    private static long getFileSize (File file) {
        if (!file.exists()) {
            return 0;
        }

        long size = 0;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return 0;
            }
            for (File f: files) {
                size += getFileSize(f);
            }
        } else {
            try {
                FileInputStream fin = new FileInputStream(file);
                FileChannel fileChannel = fin.getChannel();
                size = fileChannel.size();
                fileChannel.close();
                fin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return size;
    }

    public static int count (String path) {
        return count(new File(path));
    }

    /**
     * 文件个数，不包括文件夹
     */
    private static int count (File file) {
        if (!file.exists()) {
            return 0;
        }

        int count = 0;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return 0;
            }
            for (File f: files) {
                count += count(f);
            }
        } else {
            count ++;
        }

        return count;
    }


    public static void copyFiles (String sourcePath, String dstDir, OnCopyCallback callback) {
        FileTask task = new FileTask(new File[] {new File(sourcePath), new File(dstDir)}, TaskType.COPY, callback);
        task.execute();
    }

    /**
     * 拷贝，会拷贝所有文件，可拷贝這个目录到另一个目录下
     */
    private static void copy (String sourcePath, String dstDir, OnCopyCallback callback) {
        File sFile = new File(sourcePath);
        File dFile = new File(dstDir);

        if (!sFile.exists()) {
            return;
        }

        if (!dFile.exists()) {
            if (!dFile.mkdirs()) {
                return;
            }
        }

        if (sFile.isDirectory()) {
            File[] files = sFile.listFiles();
            if (files == null) {
                return;
            }
            String dir = dstDir + File.separator + sFile.getName();
            for (File file : files) {
                copy(file.getPath(), dir, callback);
            }
        } else {
            File file = new File(dstDir, sFile.getName());

            if (callback != null) {
                callback.onCopyFile(sFile);
            }

            try {
                long fileSize = getFileSize(sFile);
                byte[] buff = new byte[1024];
                int len = 0;
                long totalLen = 0;

                FileInputStream fin = new FileInputStream(sFile);
                FileOutputStream fout = new FileOutputStream(file);

                while ((len = fin.read(buff)) != -1) {
                    fout.write(buff, 0, len);
                    totalLen += len;
                    if (callback != null) {
                        callback.onCopying(sFile, totalLen, fileSize);
                    }
                }
                fin.close();
                fout.flush();
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFiles(String filePath, OnDeleteCallback callback) {
        FileTask fileTask = new FileTask(new File[]{new File(filePath)}, TaskType.DELETE, callback);
        fileTask.execute();
    }

    /**
     * 删除所有文件，包括文件夹下的其他文件，包括自己
     */
    private static void delete(File file, OnDeleteCallback callback) {

        if (!file.exists()) {
            return;
        }

        if (callback != null) {
            callback.onDeleteFile(file);
        }

        if (!file.isDirectory()) {
            safeDelete(file);
        }

        File[] files = file.listFiles();

        if (files == null || files.length <= 0) {
            safeDelete(file);
            return;
        }

        for (File f : files) {
            delete(f, callback);
        }

        safeDelete(file);
    }

    /**
     * 只能删除单个文件，一旦传入的参数是一个directory而且里面有其他文件，那么这个方法将无法完成删除
     */
    private static void safeDelete (File file) {
        final File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
        //重命名是为了防止文件名字超过系统限制导致的无法删除的情形
        file.renameTo(to);
        to.delete();
    }

    enum TaskType {
        COPY, DELETE;
    }

    //task to do file jobs
    static class FileTask extends AsyncTask<Integer, Integer, Boolean> {
        private File[] files;
        private TaskType taskType;
        private Object callback;

        public FileTask(File[] files, TaskType taskType, Object callback) {
            super();
            this.files = files;
            this.taskType = taskType;
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            switch (taskType) {
                case DELETE:
                    delete(files[0], (OnDeleteCallback) callback);
                    if (callback != null) ((OnDeleteCallback)callback).onDeleteFinished();
                    break;
                case COPY:
                    copy(files[0].getPath(), files[1].getPath(), (OnCopyCallback) callback);
                    if (callback != null) ((OnCopyCallback)callback).onCopyFinished();
                    break;
            }
            return true;
        }
    }

    public interface OnDeleteCallback {
        void onDeleteFile (File file);
        void onDeleteFinished ();
    }

    public interface OnCopyCallback {
        void onCopyFile (File file);
        void onCopying (File file, long copiedSize, long fileSize);
        void onCopyFinished ();
    }
}
