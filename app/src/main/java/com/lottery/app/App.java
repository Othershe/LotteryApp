package com.lottery.app;

import android.app.Application;
import android.content.Context;

import com.lottery.app.utils.SPUtil;


public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        SPUtil.init(mContext, "lottery");
    }

    public static Context getContext() {
        return mContext;
    }
}
