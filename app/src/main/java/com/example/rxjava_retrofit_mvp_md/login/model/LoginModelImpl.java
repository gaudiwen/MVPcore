package com.example.rxjava_retrofit_mvp_md.login.model;

import com.example.rxjava_retrofit_mvp_md.beans.LoginApi;
import com.example.rxjava_retrofit_mvp_md.beans.LoginBean;
import com.example.rxjava_retrofit_mvp_md.http.HttpManager;
import com.example.rxjava_retrofit_mvp_md.http.listener.HttpOnNextListener;

import me.yokeyword.fragmentation.SupportFragment;


public class LoginModelImpl implements LoginModel {
    public static final String TAG = LoginModelImpl.class.getSimpleName();


    @Override
    public void login(SupportFragment context, String user, String psw, final OnLoginListListener onLoginListListener) {

        LoginApi loginApi = new LoginApi(context,user, psw,new HttpOnNextListener<LoginBean>() {

            @Override
            public void onNext(LoginBean loginBean) {
                onLoginListListener.onSuccess(loginBean);
            }

            @Override
            public void onError(Throwable e,String s) {
                super.onError(e,s);
            }
        });
        HttpManager.getInstance().doHttpDeal(loginApi);

    }


    public interface OnLoginListListener {
        void onSuccess(LoginBean loginBean);

        void onFailure(Throwable e);

    }

}
