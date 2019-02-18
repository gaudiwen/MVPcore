package com.example.rxjava_retrofit_mvp_md.login.view;

import com.example.rxjava_retrofit_mvp_md.base.BaseView;
import com.example.rxjava_retrofit_mvp_md.beans.ArticleBody;
import com.example.rxjava_retrofit_mvp_md.beans.LoginBean;

/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public interface LoginView extends BaseView {

    void login(LoginBean loginBean);

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showLoadFailMsg(Throwable e);
}
