package app.lottery.com.lotteryapp.adapter;

import android.content.Context;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

import app.lottery.com.lotteryapp.R;
import app.lottery.com.lotteryapp.data.NewsData;

/**
 * Author: Othershe
 * Time: 2017/6/12 22:11
 */

public class NewsItemAdapter extends CommonBaseAdapter<NewsData> {
    public NewsItemAdapter(Context context, List<NewsData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, NewsData data) {
        holder.setText(R.id.title, data.getTitle());
        holder.setText(R.id.time, data.getTime());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_news_layout;
    }
}
