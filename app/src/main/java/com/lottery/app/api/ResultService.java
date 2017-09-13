package com.lottery.app.api;

import com.lottery.app.constant.Apis;

import java.util.Map;


import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:56
 */

public interface ResultService {
    String BASE_URL = Apis.URL_RESULT_ITEM;

    @GET("Haoma.aspx")
    Observable<String> getResultsItemData(@Query("page") String page);
}
