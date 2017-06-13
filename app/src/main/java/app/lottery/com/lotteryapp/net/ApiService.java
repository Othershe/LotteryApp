package app.lottery.com.lotteryapp.net;

import app.lottery.com.lotteryapp.api.NewsService;
import app.lottery.com.lotteryapp.api.ResultService;

public class ApiService {
    public static ApiService getInstance() {
        return ApiService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ApiService INSTANCE = new ApiService();
    }

    public <S> S initService(Class<S> service) {
        if (service.equals(NewsService.class) || service.equals(ResultService.class)) {
            return NetManager.getInstance().create(service);
        }
        return null;
    }
}
