package com.lottery.app.api;

import com.google.gson.JsonObject;
import com.lottery.app.constant.Apis;
import com.lottery.app.data.AdData1;


import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AdService1 {
    String BASE_URL = Apis.URL_AD1;

    @POST("lotto/api")
    Observable<AdData1> getAd(@Body JsonObject jsonObject);
}
