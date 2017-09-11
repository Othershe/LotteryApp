package com.lottery.app.presenter;


import com.google.gson.JsonObject;
import com.lottery.app.api.AdService1;
import com.lottery.app.data.AdData1;
import com.lottery.app.net.ApiService;
import com.lottery.app.net.RxManager;
import com.lottery.app.net.RxSubscriber;
import com.lottery.app.view.AdView1;


public class AdPresenter1 extends BasePresenter<AdView1> {
    public AdPresenter1(AdView1 view) {
        super(view);
    }

    public void getAd(JsonObject jsonObject) {
        mSubscription = RxManager.getInstance()
                .doSubscribe(ApiService.getInstance().initService(AdService1.class).getAd(jsonObject),
                        new RxSubscriber<AdData1>() {
                            @Override
                            protected void _onNext(AdData1 ad) {
                                mView.onSuccess(ad);
                            }

                            @Override
                            protected void _onError() {
                                mView.onError();
                            }
                        });
    }
}
