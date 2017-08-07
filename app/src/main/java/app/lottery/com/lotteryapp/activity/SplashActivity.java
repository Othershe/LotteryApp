package app.lottery.com.lotteryapp.activity;


import android.content.Intent;
import android.os.Handler;

import app.lottery.com.lotteryapp.MainActivity;
import app.lottery.com.lotteryapp.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected int initLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        startDelay();
    }

    @Override
    protected void initData() {

    }

    private void startDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
