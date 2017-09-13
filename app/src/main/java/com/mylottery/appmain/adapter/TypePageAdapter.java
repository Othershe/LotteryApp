package com.mylottery.appmain.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mylottery.appmain.fragment.BaseFragment;

import java.util.List;


/**
 * Author: Othershe
 * Time: 2016/8/12 16:02
 */
public class TypePageAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public TypePageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
