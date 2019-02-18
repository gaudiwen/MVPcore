package com.example.rxjava_retrofit_mvp_md.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    public abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
}
