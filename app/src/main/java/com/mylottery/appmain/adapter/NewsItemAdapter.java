package com.mylottery.appmain.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.mylottery.appmain.R;
import com.mylottery.appmain.data.NewsData;
import com.mylottery.appmain.utils.ImageLoader;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;


/**
 * Author: Othershe
 * Time: 2017/6/12 22:11
 */

public class NewsItemAdapter extends CommonBaseAdapter<NewsData> {
    private String[] iconUrls = {
            "http://pimg1.126.net/caipiao_info/images/headFigure/zxWapIndex/1494575075899_1.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/zxWapIndex/1453262147080_1.jpg",
            "http://img4.cache.netease.com/sports/2015/1/9/201501091043400c685.jpg",
            "http://img3.cache.netease.com/sports/2015/1/9/201501091045531df91.jpg",
            "http://img6.cache.netease.com/sports/2015/1/9/20150109104322ac429.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364583862_1.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364521563_1.jpg",
            "http://img1.cache.netease.com/sports/2015/1/9/20150109104510b3925.jpg",
            "http://img4.cache.netease.com/sports/2015/1/9/20150109104324dd86f.jpg",
            "http://img1.cache.netease.com/sports/2015/1/9/20150109104305a151d.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364599601_1.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364501382_1.jpg",
            "http://img4.cache.netease.com/sports/2015/1/9/2015010910452378a90.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364566664_1.jpg",
            "http://img6.cache.netease.com/sports/2015/1/9/20150109140330e9db6.jpg",
            "http://img2.cache.netease.com/sports/2015/1/9/2015010910434201874.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364553849_1.jpg",
            "http://img1.cache.netease.com/sports/2015/1/9/2015010910440894784.jpg",
            "http://img3.cache.netease.com/sports/2015/1/9/20150109104532ed69e.jpg",
            "http://img5.cache.netease.com/sports/2015/1/9/20150109104331211fd.jpg",
            "http://img5.cache.netease.com/sports/2015/1/9/20150109104654fc3d6.jpg",
            "http://img3.cache.netease.com/sports/2015/1/9/20150109104615f178e.jpg",
            "http://img6.cache.netease.com/sports/2015/1/9/20150109104316fea3a.jpg",
            "http://img6.cache.netease.com/sports/2015/1/9/2015010910453741570.jpg",
            "http://img4.cache.netease.com/sports/2015/1/9/20150109104617147ae.jpg",
            "http://img1.cache.netease.com/sports/2015/1/9/2015010910440894784.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364566664_1.jpg",
            "http://pimg1.126.net/caipiao_info/images/headFigure/caipiaodashi/1493364540819_1.jpg",
            "http://img1.cache.netease.com/sports/2017/5/16/201705160834124e962.png",
            "http://img5.cache.netease.com/sports/2015/1/9/20150109104331211fd.jpg",
            "http://img4.cache.netease.com/sports/2015/1/9/201501091043203e2e0.jpg",
            "http://img5.cache.netease.com/sports/2015/1/9/20150109104420bd459.jpg",
            "http://img4.cache.netease.com/sports/2015/1/9/20150109104335f1e0d.jpg",
            "http://img4.cache.netease.com/sports/2015/1/9/20150109104643be7ea.jpg",
            "http://img6.cache.netease.com/sports/2015/1/9/2015010910463962820.jpg",
            "http://img6.cache.netease.com/sports/2015/1/9/201501091046302b56a.jpg",
            "http://img2.cache.netease.com/sports/2015/1/9/20150109104645086d1.jpg",
            "http://img5.cache.netease.com/sports/2015/1/9/201501091043268e14f.jpg",
            "http://img2.cache.netease.com/sports/2015/1/9/201501091044403bd02.jpg"

    };

    private int index;

    public NewsItemAdapter(Context context, List<NewsData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, NewsData data) {
        holder.setText(R.id.title, data.getTitle());
        holder.setText(R.id.time, data.getTime());
        ImageLoader.load(mContext, iconUrls[(index++) % 39], (ImageView) holder.getView(R.id.item_icon));
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_news_layout;
    }
}
