package com.lottery.app.view;

import com.lottery.app.data.NewsData;

import java.util.List;


/**
 * Author: Othershe
 * Time: 2017/6/12 20:40
 */

public interface NewsItemView extends IBaseView {
    void onSuccess(List<NewsData> data);
}
