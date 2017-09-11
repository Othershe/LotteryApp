package com.lottery.app.api;

import com.lottery.app.constant.Apis;
import com.lottery.app.data.AdData;

import retrofit2.http.GET;
import rx.Observable;

public interface AdService {
    String BASE_URL = Apis.URL_AD;

    @GET("selectAndroidVersion")
    Observable<AdData> getAd();
}
