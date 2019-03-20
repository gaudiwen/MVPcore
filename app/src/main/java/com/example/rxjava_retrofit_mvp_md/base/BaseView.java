package com.example.rxjava_retrofit_mvp_md.base;

/**
 *Time:2018/10/13
 *Author:Gaodi.
 *Description:
 */
public interface BaseView {

    void showProgress();

    void hideProgress();

    void showLoadFailMsg(Throwable e);
}
