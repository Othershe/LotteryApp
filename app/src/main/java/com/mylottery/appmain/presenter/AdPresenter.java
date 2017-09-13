package com.mylottery.appmain.presenter;


import com.mylottery.appmain.api.AdService;
import com.mylottery.appmain.data.AdData;
import com.mylottery.appmain.net.ApiService;
import com.mylottery.appmain.net.RxManager;
import com.mylottery.appmain.net.RxSubscriber;
import com.mylottery.appmain.view.AdView;

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
