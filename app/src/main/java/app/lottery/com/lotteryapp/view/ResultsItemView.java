package app.lottery.com.lotteryapp.view;

import java.util.List;

import app.lottery.com.lotteryapp.data.ResultData;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:40
 */

public interface ResultsItemView extends IBaseView {
    void onSuccess(List<ResultData> data);
}
