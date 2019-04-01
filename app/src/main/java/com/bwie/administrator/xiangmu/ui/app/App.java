package com.bwie.administrator.xiangmu.ui.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        //定义fresco缓存路径
        Fresco.initialize(this);
    }

    public static Context getApplication() {
        return mContext;
    }
}
