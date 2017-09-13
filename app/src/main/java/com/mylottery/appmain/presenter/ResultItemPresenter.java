package com.mylottery.appmain.presenter;

import com.mylottery.appmain.api.ResultService;
import com.mylottery.appmain.net.ApiService;
import com.mylottery.appmain.net.RxManager;
import com.mylottery.appmain.net.RxSubscriber;
import com.mylottery.appmain.utils.JsoupUtil;
import com.mylottery.appmain.view.ResultsItemView;

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
