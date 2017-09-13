package com.mylottery.appmain.api;

import com.mylottery.appmain.constant.Apis;
import com.mylottery.appmain.data.AdData;

import retrofit2.http.GET;
import rx.Observable;

public interface AdService {
    String BASE_URL = Apis.URL_AD;

    @GET("selectAndroidVersion")
    Observable<AdData> getAd();
}
