package com.example.rxjava_retrofit_mvp_md.http;

import com.example.rxjava_retrofit_mvp_md.BuildConfig;
import com.example.rxjava_retrofit_mvp_md.http.api.BaseApi;
import com.example.rxjava_retrofit_mvp_md.http.converter.string.StringConverterFactory;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
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

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder .addInterceptor(httpLoggingInterceptor) //日志拦截器，打印请求日志
                .connectTimeout(15, TimeUnit.SECONDS)   // 连接超时时间60
                .writeTimeout(180, TimeUnit.SECONDS)    // 写入服务器超时时间
                .readTimeout(180, TimeUnit.SECONDS);     // 读取数据超时时间
        /*if(interceptors != null){
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }*/
        OkHttpClient okHttpClient = builder.build();


        /**
         * 注：addConverterFactory是有先后顺序的，如果有多个ConverterFactory都支持同一种类型，那么就是只有第一个才会被使用，
         * 而GsonConverterFactory是不判断是否支持的，所以这里交换了顺序还会有一个异常抛出，原因是类型不匹配。
         */
        Retrofit retrofit = new Retrofit.Builder()
               // .client(builder.build())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())    //Gson
                .addConverterFactory(new StringConverterFactory())      // String转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //支持rxjava
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
