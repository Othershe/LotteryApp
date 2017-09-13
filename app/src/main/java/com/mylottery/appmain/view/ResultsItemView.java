package com.mylottery.appmain.view;

import com.mylottery.appmain.data.ResultData;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2017/6/12 20:40
 */

public interface ResultsItemView extends IBaseView {
    void onSuccess(List<ResultData> data);
}
