package com.lottery.app.presenter;

import android.util.Log;

import com.lottery.app.api.ResultService;
import com.lottery.app.net.ApiService;
import com.lottery.app.net.RxManager;
import com.lottery.app.net.RxSubscriber;
import com.lottery.app.utils.DateUtil;
import com.lottery.app.utils.JsoupUtil;
import com.lottery.app.view.ResultsItemView;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:50
 */

public class ResultItemPresenter extends BasePresenter<ResultsItemView> {
    public ResultItemPresenter(ResultsItemView view) {
        super(view);
    }

    public void getResultsItemData(int pageNum) {
        Map<String, String> map = new HashMap<>();
        String[] date = DateUtil.getDate(pageNum);
        Log.e("sss", date[0] + "---" + date[1] + "----" + pageNum);
        map.put("type", "range_date");
        map.put("start", date[0]);
        map.put("end", date[1]);
        mSubscription = RxManager.getInstance()
                .doSubscribe(ApiService.getInstance().initService(ResultService.class).getResultsItemData(map),
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
