package com.example.rxjava_retrofit_mvp_md.delegates;

import android.os.Bundle;

import com.example.rxjava_retrofit_mvp_md.R;


/**
 *Time:2019/2/15
 *Author:Gaodi.
 *Description:
 */
public class SplashDelegate extends PlaneDelegate {

    public static SplashDelegate newInstance() {

        Bundle args = new Bundle();

        SplashDelegate fragment = new SplashDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_splash;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void init() {

        setSwipeBackEnable(false);
    }
}
