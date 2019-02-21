package com.example.rxjava_retrofit_mvp_md.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import duofriend.com.paperplane.app.Plane;
import duofriend.com.paperplane.app.SystemType;


public class MyApplication extends Application {
    private static ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        Plane.init(this)
                .withApiHostType(SystemType.YIDIAN)  //翼店
                .withApiHost(SystemType.FORMAL_TYPE) //正式环境
                .withLogcatSwitch(true)
                .configure();

    }


    public static ImageLoader getImageLoader(Context context) {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = ImageLoader.getInstance();
                    mImageLoader.init(ImageLoaderConfiguration.createDefault(context.
                            getApplicationContext()));
                }
            }
        }
        return mImageLoader;
    }
}
