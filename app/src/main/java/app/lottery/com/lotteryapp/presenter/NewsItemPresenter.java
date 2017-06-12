package app.lottery.com.lotteryapp.presenter;

import java.util.HashMap;
import java.util.Map;

import app.lottery.com.lotteryapp.api.NewsService;
import app.lottery.com.lotteryapp.net.ApiService;
import app.lottery.com.lotteryapp.net.RxManager;
import app.lottery.com.lotteryapp.net.RxSubscriber;
import app.lottery.com.lotteryapp.utils.JsoupUtil;
import app.lottery.com.lotteryapp.view.NewsItemView;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:50
 */

public class NewsItemPresenter extends BasePresenter<NewsItemView> {
    public NewsItemPresenter(NewsItemView view) {
        super(view);
    }

    public void getNewsItemData(int pageNum) {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("infoClass", "news");
        map.put("gameId", "");
        mSubscription = RxManager.getInstance()
                .doSubscribe(ApiService.getInstance().initService(NewsService.class).getNewsItemData(map),
                        new RxSubscriber<String>() {
                            @Override
                            protected void _onNext(String s) {
                                mView.onSuccess(JsoupUtil.parseNews(s));
                            }

                            @Override
                            protected void _onError() {
                                mView.onError();
                            }
                        });
    }
}
