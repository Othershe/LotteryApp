package app.lottery.com.lotteryapp.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import app.lottery.com.lotteryapp.api.NewsService;
import app.lottery.com.lotteryapp.api.ResultService;
import app.lottery.com.lotteryapp.net.ApiService;
import app.lottery.com.lotteryapp.net.RxManager;
import app.lottery.com.lotteryapp.net.RxSubscriber;
import app.lottery.com.lotteryapp.utils.DateUtil;
import app.lottery.com.lotteryapp.utils.JsoupUtil;
import app.lottery.com.lotteryapp.view.NewsItemView;
import app.lottery.com.lotteryapp.view.ResultsItemView;

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
