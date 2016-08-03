package com.mapsocial.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gy.appbase.activity.BaseFragmentActivity;
import com.gy.appbase.controller.BaseFragmentActivityController;
import com.mapsocial.R;

public class MainActivity extends BaseFragmentActivity {

    public static void startSelf (Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setContent(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected BaseFragmentActivityController instanceController() {
        return null;
    }

}
