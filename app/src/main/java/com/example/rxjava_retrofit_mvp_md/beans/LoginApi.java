package com.example.rxjava_retrofit_mvp_md.beans;

import android.content.Context;

import com.example.rxjava_retrofit_mvp_md.http.HttpService;
import com.example.rxjava_retrofit_mvp_md.http.api.BaseApi;
import com.example.rxjava_retrofit_mvp_md.http.listener.HttpOnNextListener;
import com.example.rxjava_retrofit_mvp_md.login.model.LoginModelImpl;

import java.util.HashMap;
import java.util.Map;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Time:{DATE}
 * Author:Gaodi.
 * Description:
 */
public class LoginApi extends BaseApi {

    private String account;
    private String psw;

    public LoginApi(SupportFragment context, String account, String psw, HttpOnNextListener listener) {
        super(context,listener);
        this.account=account;
        this.psw=psw;
        setShowProgress(true);

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("style", "0");
        loginMap.put("userName", account);
        loginMap.put("password", psw);
        HttpService service = retrofit.create(HttpService.class);
        return service.getLoginToken(loginMap);
    }
}
