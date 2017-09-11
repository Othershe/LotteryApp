package com.lottery.app.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottery.app.R;
import com.lottery.app.data.ResultData;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;



/**
 * Author: Othershe
 * Time: 2017/6/12 22:11
 */

public class ResultItemAdapter extends CommonBaseAdapter<ResultData> {
    public ResultItemAdapter(Context context, List<ResultData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, ResultData data) {
        holder.setText(R.id.result_num, "第" + data.getNum() + "期");
        holder.setText(R.id.result_date, data.getDate());
        LinearLayout balls = holder.getView(R.id.balls);
        for (int i = 0; i < 7; i++) {
            ((TextView) balls.getChildAt(i)).setText(data.getBalls().substring(i*2, (i+1)*2));
        }
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_results_layout;
    }
}
