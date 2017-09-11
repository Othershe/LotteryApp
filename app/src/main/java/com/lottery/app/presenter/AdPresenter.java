package com.lottery.app.presenter;


import com.lottery.app.api.AdService;
import com.lottery.app.data.AdData;
import com.lottery.app.net.ApiService;
import com.lottery.app.net.RxManager;
import com.lottery.app.net.RxSubscriber;
import com.lottery.app.view.AdView;

public class AdPresenter extends BasePresenter<AdView> {
    public AdPresenter(AdView view) {
        super(view);
    }

    public void getAd() {
        mSubscription = RxManager.getInstance()
                .doSubscribe(ApiService.getInstance().initService(AdService.class).getAd(),
                        new RxSubscriber<AdData>() {
                            @Override
                            protected void _onNext(AdData s) {
                                mView.onSuccess(s);
                            }

                            @Override
                            protected void _onError() {
                                mView.onError();
                            }
                        });
    }
}
