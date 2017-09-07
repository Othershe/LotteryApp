package app.lottery.com.lotteryapp.presenter;


import com.google.gson.JsonObject;

import app.lottery.com.lotteryapp.api.AdService1;
import app.lottery.com.lotteryapp.net.ApiService;
import app.lottery.com.lotteryapp.net.RxManager;
import app.lottery.com.lotteryapp.net.RxSubscriber;
import app.lottery.com.lotteryapp.view.AdView1;

public class AdPresenter1 extends BasePresenter<AdView1> {
    public AdPresenter1(AdView1 view) {
        super(view);
    }

    public void getAd(JsonObject jsonObject) {
        mSubscription = RxManager.getInstance()
                .doSubscribe(ApiService.getInstance().initService(AdService1.class).getAd(jsonObject),
                        new RxSubscriber<String>() {
                            @Override
                            protected void _onNext(String s) {
                                mView.onSuccess(s);
                            }

                            @Override
                            protected void _onError() {
                                mView.onError();
                            }
                        });
    }
}
