package com.example.rxjava_retrofit_mvp_md.login.presenter;

import android.widget.Toast;

import com.example.rxjava_retrofit_mvp_md.beans.LoginBean;
import com.example.rxjava_retrofit_mvp_md.login.model.LoginModel;
import com.example.rxjava_retrofit_mvp_md.login.model.LoginModelImpl;
import com.example.rxjava_retrofit_mvp_md.login.view.LoginView;

import duofriend.com.paperplane.utils.commonutil.ToastUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginModelImpl.OnLoginListListener{
    private SupportFragment mContext;
    private LoginModel mLoginModel;
    private LoginView mLoginView;

    public LoginPresenterImpl(SupportFragment context, LoginView loginView) {
        this.mContext = context;
        this.mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }


    @Override
    public void onSuccess(LoginBean loginBean) {

        mLoginView.login(loginBean);
    }

    @Override
    public void onFailure(String e) {
        ToastUtil.getInstance().showToast("请求失败:"+e);
    }


    @Override
    public void Login(String user, String psw) {

        mLoginModel.login(mContext,user,psw,this);
    }
}
