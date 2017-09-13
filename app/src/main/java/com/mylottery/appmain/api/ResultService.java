package com.mylottery.appmain.api;

import com.mylottery.appmain.constant.Apis;


import retrofit2.http.GET;
import retrofit2.http.Query;
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
