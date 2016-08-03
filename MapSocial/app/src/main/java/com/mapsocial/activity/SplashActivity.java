package com.mapsocial.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.gy.appbase.activity.BaseFragmentActivity;
import com.gy.appbase.controller.BaseFragmentActivityController;
import com.gy.appbase.inject.ViewInject;
import com.mapsocial.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseFragmentActivity {

    @ViewInject (R.id.tv_timer) private TextView timeText;

    private Timer timer;
    private int count = 2;

    @Override
    protected void setContent(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        timer = new Timer("count_down");
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    protected BaseFragmentActivityController instanceController() {
        return null;
    }

    //倒计时
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            SplashActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    count--;
                    if (count <= 0) {
                        //倒计时结束后进入主页，并释放资源
                        timer.cancel();
                        MainActivity.startSelf(SplashActivity.this);
                        SplashActivity.this.finish();
                        return;
                    }
                    timeText.setText(""+count);
                }
            });
        }
    };
}
