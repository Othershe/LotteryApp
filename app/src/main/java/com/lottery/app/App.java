package com.lottery.app;

import android.app.Application;
import android.content.Context;

import com.lottery.app.utils.SPUtil;

import cn.jpush.android.api.JPushInterface;


public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        SPUtil.init(mContext, "lottery");

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
