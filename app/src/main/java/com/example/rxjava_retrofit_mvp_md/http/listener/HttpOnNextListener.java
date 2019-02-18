package com.example.rxjava_retrofit_mvp_md.http.listener;

/**
 * Created by KomoriWu
 * on 2017-03-26.
 */

public abstract class HttpOnNextListener<T> {
    public abstract void onNext(T t);

    public void onError(Throwable e,String HttpException) {

    }

    public void onCancel() {

    }

}
