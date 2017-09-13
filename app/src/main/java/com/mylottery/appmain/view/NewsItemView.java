package com.mylottery.appmain.view;

import com.mylottery.appmain.data.NewsData;

import java.util.List;


/**
 * Author: Othershe
 * Time: 2017/6/12 20:40
 */

public interface NewsItemView extends IBaseView {
    void onSuccess(List<NewsData> data);
}
