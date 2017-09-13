package com.mylottery.appmain.api;

import com.google.gson.JsonObject;
import com.mylottery.appmain.constant.Apis;
import com.mylottery.appmain.data.AdData1;


import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AdService1 {
    String BASE_URL = Apis.URL_AD1;

    @POST("lotto/api")
    Observable<AdData1> getAd(@Body JsonObject jsonObject);
}
