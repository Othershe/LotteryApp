package app.lottery.com.lotteryapp.presenter;


import app.lottery.com.lotteryapp.api.AdService;
import app.lottery.com.lotteryapp.data.AdData;
import app.lottery.com.lotteryapp.net.ApiService;
import app.lottery.com.lotteryapp.net.RxManager;
import app.lottery.com.lotteryapp.net.RxSubscriber;
import app.lottery.com.lotteryapp.view.AdView;

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
