package app.lottery.com.lotteryapp.presenter;


import app.lottery.com.lotteryapp.view.IBaseView;
import rx.Subscription;

/**
 * Author: Othershe
 * Time:  2016/8/11 11:17
 */
public class BasePresenter<V extends IBaseView> {
    public V mView;
    protected Subscription mSubscription;

    public BasePresenter(V view){
        mView = view;
    }

    public void detach() {
        mView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
