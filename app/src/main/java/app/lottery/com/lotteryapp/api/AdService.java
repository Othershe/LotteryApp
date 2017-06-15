package app.lottery.com.lotteryapp.api;

import app.lottery.com.lotteryapp.constant.Apis;
import app.lottery.com.lotteryapp.data.AdData;
import retrofit2.http.GET;
import rx.Observable;

public interface AdService {
    String BASE_URL = Apis.URL_AD;

    @GET("selectAndroidVersion")
    Observable<AdData> getAd();
}
