package com.mapsocial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gy.appbase.controller.BaseFragmentActivityController;
import com.gy.appbase.fragment.BaseFragment;
import com.gy.appbase.inject.ViewInject;
import com.mapsocial.R;
import com.mapsocial.constant.Consts;
import com.mapsocial.controller.MainActivityCtrl;

/**
 * Created by ganyu on 2016/8/3.
 *
 */
public class MapFragment extends BaseFragment{

    @ViewInject (R.id.tv_title)     TextView mTvTitle;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        mTvTitle.setTypeface(Consts.getTypefaceGirl(mActivity));
        mTvTitle.setText(getString(R.string.app_name));
    }

    @Override
    protected BaseFragmentActivityController instanceController() {
        return new MainActivityCtrl(mActivity);
    }
}
