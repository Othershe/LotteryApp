package com.lottery.app.presenter;

import com.lottery.app.api.NewsService;
import com.lottery.app.net.ApiService;
import com.lottery.app.net.RxManager;
import com.lottery.app.net.RxSubscriber;
import com.lottery.app.utils.JsoupUtil;
import com.lottery.app.view.NewsItemView;

import java.util.HashMap;
import java.util.Map;


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
