package com.lottery.app.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lottery.app.R;
import com.lottery.app.activity.NewsDetailActivity;
import com.lottery.app.adapter.NewsItemAdapter;
import com.lottery.app.data.NewsData;
import com.lottery.app.presenter.NewsItemPresenter;
import com.lottery.app.view.NewsItemView;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dell on 2017/6/12.
 */

public class NewsFragment extends BaseMvpFragment implements NewsItemView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.news_list)
    RecyclerView mNewsList;

    @BindView(R.id.swipfreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsItemPresenter newsItemPresenter;

    private NewsItemAdapter newsItemAdapter;

    private int PAGE_COUNT = 1;
    private boolean isLoadMore;
    private int mTempPageCount = 2;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected void fetchData() {
        newsItemPresenter = new NewsItemPresenter(this);
        addPresenter(newsItemPresenter);
        newsItemPresenter.getNewsItemData(PAGE_COUNT);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_news_layout;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);

        newsItemAdapter = new NewsItemAdapter(mActivity, null, true);
        newsItemAdapter.setLoadingView(R.layout.load_loading_layout);
        newsItemAdapter.setOnItemClickListener(new OnItemClickListener<NewsData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, NewsData data, int position) {
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("news_data", data);
                mActivity.startActivity(intent);
            }
        });

        newsItemAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (PAGE_COUNT == mTempPageCount && !isReload) {
                    return;
                }
                isLoadMore = true;
                PAGE_COUNT = mTempPageCount;
                fetchData();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNewsList.setLayoutManager(layoutManager);

        mNewsList.setAdapter(newsItemAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(List<NewsData> data) {
        if (isLoadMore) {
            if (data.size() == 0) {
                newsItemAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                newsItemAdapter.setLoadMoreData(data);
                mTempPageCount++;
            }
        } else {
            newsItemAdapter.setNewData(data);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError() {
        if (isLoadMore) {
            newsItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        PAGE_COUNT = 1;
        mTempPageCount = 2;
        fetchData();
    }
}
