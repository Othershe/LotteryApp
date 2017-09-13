package com.lottery.app.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lottery.app.R;
import com.lottery.app.adapter.ResultItemAdapter;
import com.lottery.app.data.ResultData;
import com.lottery.app.presenter.ResultItemPresenter;
import com.lottery.app.view.ResultsItemView;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;

import java.util.List;


import butterknife.BindView;

/**
 * Created by dell on 2017/6/12.
 */

public class ResultsFragment extends BaseMvpFragment implements ResultsItemView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.results_list)
    RecyclerView mNewsList;

    @BindView(R.id.swipfreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ResultItemPresenter resultItemPresenter;

    private ResultItemAdapter resultItemAdapter;

    private int PAGE_COUNT = 1;
    private boolean isLoadMore;
    private int mTempPageCount = 2;

    public static ResultsFragment newInstance() {
        return new ResultsFragment();
    }

    @Override
    protected void fetchData() {
        resultItemPresenter = new ResultItemPresenter(this);
        addPresenter(resultItemPresenter);
        resultItemPresenter.getResultsItemData(PAGE_COUNT);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_results_layout;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);

        resultItemAdapter = new ResultItemAdapter(mActivity, null, true);
        resultItemAdapter.setLoadingView(R.layout.load_loading_layout);
        resultItemAdapter.setOnItemClickListener(new OnItemClickListener<ResultData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, ResultData data, int position) {

            }
        });

        resultItemAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
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

        mNewsList.setAdapter(resultItemAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(List<ResultData> data) {
        if (isLoadMore) {
            if (data.size() == 0) {
                resultItemAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                resultItemAdapter.setLoadMoreData(data);
                mTempPageCount++;
            }
        } else {
            resultItemAdapter.setNewData(data);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError() {
        if (isLoadMore) {
            resultItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
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
