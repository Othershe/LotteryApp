package app.lottery.com.lotteryapp.fragment;

import app.lottery.com.lotteryapp.R;

/**
 * Created by dell on 2017/6/12.
 */

public class NewsFragment extends BaseMvpFragment {
    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_news_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
