package app.lottery.com.lotteryapp;

import android.app.Application;
import android.content.Context;

import app.lottery.com.lotteryapp.utils.SPUtil;

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
