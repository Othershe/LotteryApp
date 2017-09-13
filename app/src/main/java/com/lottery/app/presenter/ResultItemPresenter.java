package com.lottery.app.presenter;

import com.lottery.app.api.ResultService;
import com.lottery.app.net.ApiService;
import com.lottery.app.net.RxManager;
import com.lottery.app.net.RxSubscriber;
import com.lottery.app.utils.JsoupUtil;
import com.lottery.app.view.ResultsItemView;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:50
 */

public class ResultItemPresenter extends BasePresenter<ResultsItemView> {
    public ResultItemPresenter(ResultsItemView view) {
        super(view);
    }

    public void getResultsItemData(int page) {

        mSubscription = RxManager.getInstance()
                .doSubscribe(ApiService.getInstance().initService(ResultService.class).getResultsItemData(page + ""),
                        new RxSubscriber<String>() {
                            @Override
                            protected void _onNext(String s) {
                                mView.onSuccess(JsoupUtil.parseResults(s));
                            }

                            @Override
                            protected void _onError() {
                                mView.onError();
                            }
                        });
    }
}
