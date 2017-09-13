package com.mylottery.appmain.presenter;

import com.mylottery.appmain.api.NewsService;
import com.mylottery.appmain.net.ApiService;
import com.mylottery.appmain.net.RxManager;
import com.mylottery.appmain.net.RxSubscriber;
import com.mylottery.appmain.utils.JsoupUtil;
import com.mylottery.appmain.view.NewsItemView;

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
