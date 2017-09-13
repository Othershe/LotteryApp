package com.mylottery.appmain.presenter;


import com.google.gson.JsonObject;
import com.mylottery.appmain.api.AdService1;
import com.mylottery.appmain.data.AdData1;
import com.mylottery.appmain.net.ApiService;
import com.mylottery.appmain.net.RxManager;
import com.mylottery.appmain.net.RxSubscriber;
import com.mylottery.appmain.view.AdView1;


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
