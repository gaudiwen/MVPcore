package com.example.rxjava_retrofit_mvp_md.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 *Time:2018/10/13
 *Author:Gaodi.
 *Description:
 */
public abstract class BaseFragment extends Fragment {

    public abstract View initView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = initView(inflater, container, savedInstanceState);
        return view;
    }
}
