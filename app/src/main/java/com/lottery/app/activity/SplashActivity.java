package com.lottery.app.activity;


import android.content.Intent;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lottery.app.MainActivity;
import com.lottery.app.R;
import com.lottery.app.data.AdData1;
import com.lottery.app.presenter.AdPresenter1;
import com.lottery.app.view.AdView1;

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

    }

    @Override
    public void onSuccess(AdData1 ad) {
        String url = ad.getResult().getWapUrl();
        int is = ad.getResult().getShowWap();
        if (1 == is) {//跳转

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

        finish();
    }
}
