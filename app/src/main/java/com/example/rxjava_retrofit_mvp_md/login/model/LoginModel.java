package com.example.rxjava_retrofit_mvp_md.login.model;


import me.yokeyword.fragmentation.SupportFragment;


public interface LoginModel {
    void login(SupportFragment context, String user, String psw, LoginModelImpl.
            OnLoginListListener onLoginListListener);
}
