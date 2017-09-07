package app.lottery.com.lotteryapp.api;

import com.google.gson.JsonObject;

import app.lottery.com.lotteryapp.constant.Apis;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AdService1 {
    String BASE_URL = Apis.URL_AD1;

    @POST("lotto/api")
    Observable<String> getAd(@Body JsonObject jsonObject);
}
