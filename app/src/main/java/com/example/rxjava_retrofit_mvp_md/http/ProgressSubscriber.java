package com.example.rxjava_retrofit_mvp_md.http;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.example.rxjava_retrofit_mvp_md.http.api.BaseApi;
import com.example.rxjava_retrofit_mvp_md.http.listener.HttpOnNextListener;
import com.example.rxjava_retrofit_mvp_md.utils.Utils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


public class ProgressSubscriber<T> extends Subscriber<T> {
    private boolean mIsShowProgress;
    private HttpOnNextListener mHttpOnNextListener;
    private RxAppCompatActivity mRxAppCompatActivity;
    private SupportFragment mSupportFragment;
    private ProgressDialog mProgressDialog;
    private BaseApi mApi;

    public ProgressSubscriber(BaseApi api) {
        this.mApi = api;
        this.mIsShowProgress = api.isShowProgress();
        this.mHttpOnNextListener = api.getListener();
        this.mRxAppCompatActivity = api.getRxAppCompatActivity();
        this.mSupportFragment =api.getSupportFragment();
        if (api.isShowProgress()) {
            initProgressDialog(api.isIsCancel());
        }
    }

    private void initProgressDialog(boolean cancel) {
        if (mProgressDialog == null) {
            if(mSupportFragment!=null){

                mProgressDialog = new ProgressDialog(mSupportFragment.getActivity());
                mProgressDialog.setCancelable(cancel);
                if (cancel) {
                    mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            if (mHttpOnNextListener != null) {
                                mHttpOnNextListener.onCancel();
                            }
                            onCancelProgress();
                        }
                    });
                }
            }
        }
    }

    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    private void showProgressDialog() {
        if (!mIsShowProgress) return;
        if (mProgressDialog == null || mSupportFragment == null) return;
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (!mIsShowProgress) return;
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        String s = e.getMessage();
        if (e instanceof HttpException) {
            s = "网络异常";
        } else if (e instanceof SocketTimeoutException) {  //VPN open
            s = "服务器响应超时";
        } else if (e instanceof ConnectException) {
            s = "连接服务器异常";
        } else if (e instanceof UnknownHostException) {
            s = "无网络连接，请检查网络是否开启";
        } else if (e instanceof UnknownServiceException) {
            s = "未知的服务器错误";
        } else if (e instanceof IOException) {  //飞行模式等
            s = "连接服务器异常";
        }
      /*  if (mHttpOnNextListener != null) {
            mHttpOnNextListener.onError(e,s);
            Utils.showAlertDialog(mRxAppCompatActivity, e.getMessage());
        }*/
    }

    @Override
    public void onNext(T t) {
        if (mHttpOnNextListener != null) {
            mHttpOnNextListener.onNext(t);
        }
    }

}
