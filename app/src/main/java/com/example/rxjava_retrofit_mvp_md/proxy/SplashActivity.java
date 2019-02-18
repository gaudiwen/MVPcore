package com.example.rxjava_retrofit_mvp_md.proxy;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.rxjava_retrofit_mvp_md.delegates.PlaneDelegate;
import com.example.rxjava_retrofit_mvp_md.delegates.SplashDelegate;

public class SplashActivity extends ProxyActivity {


    @Override
    public PlaneDelegate setRootDelegate() {

        return SplashDelegate.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {

    }
}
