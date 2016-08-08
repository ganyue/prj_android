package com.mapsocial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gy.appbase.activity.BaseFragmentActivity;
import com.gy.appbase.controller.BaseFragmentActivityController;
import com.gy.appbase.inject.ViewInject;
import com.mapsocial.R;
import com.mapsocial.constant.Consts;
import com.mapsocial.controller.MainActivityCtrl;
import com.mapsocial.fragment.ChatFragment;
import com.mapsocial.fragment.MapFragment;
import com.mapsocial.fragment.MineFragment;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener{

    public static void startSelf (Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @ViewInject (R.id.tv_chat)      TextView mTvChat;
    @ViewInject (R.id.tv_nearby)    TextView mTvNearby;
    @ViewInject (R.id.tv_mine)      TextView mTvMine;
    @ViewInject (R.id.iv_chat)      ImageView mIvChat;
    @ViewInject (R.id.iv_nearby)    ImageView mIvNearby;
    @ViewInject (R.id.iv_mine)      ImageView mIvMine;
    @ViewInject (R.id.llyt_chat)    LinearLayout mLlytChat;
    @ViewInject (R.id.llyt_nearby)  LinearLayout mLlytNearby;
    @ViewInject (R.id.llyt_mine)    LinearLayout mLlytMine;

    @Override
    protected void setContent(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setTransluentStatusAndNavigation(true);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mTvChat.setTypeface(Consts.getTypefaceGirl(this));
        mTvNearby.setTypeface(Consts.getTypefaceGirl(this));
        mTvMine.setTypeface(Consts.getTypefaceGirl(this));

        mLlytChat.setOnClickListener(this);
        mLlytNearby.setOnClickListener(this);
        mLlytMine.setOnClickListener(this);
        changeSelectTab(R.id.llyt_nearby);
    }

    @Override
    protected BaseFragmentActivityController instanceController() {
        return new MainActivityCtrl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_chat:
                changeSelectTab(R.id.llyt_chat);
                break;
            case R.id.llyt_nearby:
                changeSelectTab(R.id.llyt_nearby);
                break;
            case R.id.llyt_mine:
                changeSelectTab(R.id.llyt_mine);
                break;
        }
    }

    public void changeSelectTab (int id) {
        mIvChat.setImageResource(R.mipmap.ic_chat_unclicked);
        mIvNearby.setImageResource(R.mipmap.ic_nearby_unclicked);
        mIvMine.setImageResource(R.mipmap.ic_mine_unclicked);

        switch (id) {
            case R.id.llyt_chat:
                mIvChat.setImageResource(R.mipmap.ic_chat_clicked);
                mController.showFragment(true, null, R.id.flyt_content, ChatFragment.class, null, null);
                break;
            case R.id.llyt_nearby:
                mIvNearby.setImageResource(R.mipmap.ic_nearby_clicked);
                mController.showFragment(true, null, R.id.flyt_content, MapFragment.class, null, null);
                break;
            case R.id.llyt_mine:
                mIvMine.setImageResource(R.mipmap.ic_mine_clicked);
                mController.showFragment(true, null, R.id.flyt_content, MineFragment.class, null, null);
                break;
        }
    }
}
