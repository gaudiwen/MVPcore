package com.example.rxjava_retrofit_mvp_md.http.Interceptor;

import android.text.TextUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *Time:2019/2/20
 *Author:Gaodi.
 *Description:Token拦截器
 */
public class TokenHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        /**本地缓存拿Token**/
        String token ="本地缓存拿Token";
        //String token = LocalDataManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest);
        }else {
            Request originalRequest = chain.request();
            Request updateRequest = originalRequest.newBuilder().header("Authorization", token).build();
            return chain.proceed(updateRequest);
        }
    }

}