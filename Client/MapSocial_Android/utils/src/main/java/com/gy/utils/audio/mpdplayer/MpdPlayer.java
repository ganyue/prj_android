package com.gy.utils.audio.mpdplayer;

import com.gy.utils.audio.IAudioPlayer;
import com.gy.utils.audio.Playlist;

/**
 * Created by ganyu on 2016/8/3.
 *
 */
public class MpdPlayer implements IAudioPlayer {

    @Override
    public boolean initPlaylist(Playlist playlist) {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean playOrPause() {
        return false;
    }

    @Override
    public boolean prev() {
        return false;
    }

    @Override
    public boolean next() {
        return false;
    }

    @Override
    public boolean seek(int pos) {
        return false;
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void setMode(int mode) {

    }

    @Override
    public int getMode() {
        return 0;
    }

    @Override
    public void setVolume(int volume) {

    }

    @Override
    public int getVolume() {
        return 0;
    }

    @Override
    public Playlist getPlaylist() {
        return null;
    }
}
