package com.lottery.app.api;

import com.lottery.app.constant.Apis;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:56
 */

public interface NewsService {
    String BASE_URL = Apis.URL_NEWS_ITEM;

    @GET("more_news.html")
    Observable<String> getNewsItemData(@QueryMap Map<String, String> map);
}
