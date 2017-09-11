package com.lottery.app.fragment;

import android.os.Bundle;

import com.lottery.app.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Othershe
 * Time: 2016/8/12 12:19
 */
public abstract class BaseMvpFragment extends BaseFragment {

    protected List<BasePresenter> mPresenters = new ArrayList<>();

    protected abstract void fetchData();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fetchData();
    }

    protected void addPresenter(BasePresenter presenter) {
        mPresenters.add(presenter);
    }

    @Override
    public void onDestroy() {
        for (BasePresenter p : mPresenters) {
            p.detach();
        }
        super.onDestroy();
    }
}
