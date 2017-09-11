package com.lottery.app.net;

import com.lottery.app.api.AdService;
import com.lottery.app.api.AdService1;
import com.lottery.app.api.NewsService;
import com.lottery.app.api.ResultService;

public class ApiService {
    public static ApiService getInstance() {
        return ApiService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ApiService INSTANCE = new ApiService();
    }

    public <S> S initService(Class<S> service) {
        if (service.equals(NewsService.class)
                || service.equals(ResultService.class)) {
            return NetManager.getInstance().create(service);
        }else if (service.equals(AdService.class)){
            return NetManager.getInstance().create1(service);
        }else if (service.equals(AdService1.class)){
            return NetManager.getInstance().create1(service);
        }
        return null;
    }
}
