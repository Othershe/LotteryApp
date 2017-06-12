package app.lottery.com.lotteryapp.view;

import java.util.List;

import app.lottery.com.lotteryapp.data.NewsData;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:40
 */

public interface NewsItemView extends IBaseView {
    void onSuccess(List<NewsData> data);
}
