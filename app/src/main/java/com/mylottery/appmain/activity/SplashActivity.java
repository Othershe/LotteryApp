package com.mylottery.appmain.activity;


import android.content.Intent;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mylottery.appmain.MainActivity;
import com.mylottery.appmain.R;
import com.mylottery.appmain.data.AdData1;
import com.mylottery.appmain.presenter.AdPresenter1;
import com.mylottery.appmain.view.AdView1;

import org.json.JSONException;
import org.json.JSONObject;


public class SplashActivity extends BaseMvpActivity implements AdView1 {

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

    }

    @Override
    protected void fetchData() {
        AdPresenter1 presenter1 = new AdPresenter1(this);
        addPresenter(presenter1);
        JSONObject object = new JSONObject();
        try {
            object.put("jsonrpc", "2.0");
            object.put("method", "lotto.monitor");
            object.put("id", "1");

            JSONObject object1 = new JSONObject();
            object1.put("appid", "com.lottery.app");
            object.put("params", object1);
            JsonObject obj = new Gson().fromJson(object.toString(), JsonObject.class);

            presenter1.getAd(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    @Override
    public void onSuccess(AdData1 ad) {
        final String url = ad.getResult().getWapUrl();
        int is = ad.getResult().getShowWap();
        if (1 == is) {//跳转
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mContext, MainAdActivity.class);
                    intent.putExtra("ad_url", url);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }
}
