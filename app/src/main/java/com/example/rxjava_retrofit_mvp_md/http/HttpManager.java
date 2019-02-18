package com.example.rxjava_retrofit_mvp_md.http;

import com.example.rxjava_retrofit_mvp_md.BuildConfig;
import com.example.rxjava_retrofit_mvp_md.http.api.BaseApi;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KomoriWu
 * on 2017-03-26.
 */

public class HttpManager {
    private static HttpManager mHttpManager;

    public HttpManager() {
    }

    public static HttpManager getInstance() {
        if (mHttpManager == null) {
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager();
                }
            }
        }
        return mHttpManager;
    }

    public void doHttpDeal(BaseApi baseApi) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别

            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }


        builder.connectTimeout(baseApi.getConnectionTime(), TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseApi.getBaseUrl())
                .build();
        ProgressSubscriber subscriber = new ProgressSubscriber(baseApi);
        Observable observable = baseApi.getObservable(retrofit)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
              //  .compose(baseApi.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.DESTROY));
        observable.subscribe(subscriber);


    }

}
