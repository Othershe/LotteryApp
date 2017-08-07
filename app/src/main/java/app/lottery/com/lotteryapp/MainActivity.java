package app.lottery.com.lotteryapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.lottery.com.lotteryapp.activity.AdDetailActivity;
import app.lottery.com.lotteryapp.activity.BaseMvpActivity;
import app.lottery.com.lotteryapp.activity.SetActivity;
import app.lottery.com.lotteryapp.adapter.TypePageAdapter;
import app.lottery.com.lotteryapp.data.AdData;
import app.lottery.com.lotteryapp.fragment.BaseFragment;
import app.lottery.com.lotteryapp.fragment.NewsFragment;
import app.lottery.com.lotteryapp.fragment.PlayFragment;
import app.lottery.com.lotteryapp.fragment.ResultsFragment;
import app.lottery.com.lotteryapp.fragment.SetFragment;
import app.lottery.com.lotteryapp.presenter.AdPresenter;
import app.lottery.com.lotteryapp.utils.ImageLoader;
import app.lottery.com.lotteryapp.utils.SPUtil;
import app.lottery.com.lotteryapp.view.AdView;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity implements AdView {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.title)
    TextView mTitle;

    @OnClick(R.id.setting)
    void onClick() {
        Intent intent = new Intent(mContext, SetActivity.class);
        startActivity(intent);
    }

    private int lastPos = -1;
    private int currentPos = 0;

    AlertDialog dialog;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastPos = currentPos;
                currentPos = position;
                changeStatus();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(3);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_one:
                        if (currentPos == 0) {
                            return;
                        }
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.radio_two:
                        if (currentPos == 1) {
                            return;
                        }
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.radio_three:
                        if (currentPos == 2) {
                            return;
                        }
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });

        if ((Boolean) SPUtil.get(SetFragment.AD, false)) {

        }
    }

    @Override
    protected void initData() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(NewsFragment.newInstance());
        fragments.add(ResultsFragment.newInstance());
        fragments.add(PlayFragment.newInstance());

        TypePageAdapter adapter = new TypePageAdapter(getSupportFragmentManager());
        adapter.setData(fragments);
        mViewPager.setAdapter(adapter);
    }

    private void changeStatus() {
        if (currentPos == 0) {
            mTitle.setText("彩票资讯");
        } else if (currentPos == 1) {
            mTitle.setText("开奖结果");
        } else {
            mTitle.setText("模拟选号");
        }
        ((RadioButton) mRadioGroup.getChildAt(lastPos)).setTextColor(Color.parseColor("#999999"));
        ((RadioButton) mRadioGroup.getChildAt(currentPos)).setChecked(true);
        ((RadioButton) mRadioGroup.getChildAt(currentPos))
                .setTextColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onSuccess(final AdData data) {
        if (data.getFlag() == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            View view = LayoutInflater.from(mContext).inflate(R.layout.ad_layout, null);
            ImageView close = (ImageView) view.findViewById(R.id.ad_close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            ImageView ad = (ImageView) view.findViewById(R.id.ad);
            ImageLoader.load(mContext, data.getPicUrl(), ad);
            ad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AdDetailActivity.class);
                    intent.putExtra("ad_url", data.getAppUrl());
                    startActivity(intent);

                    dialog.dismiss();
                }
            });
            builder.setView(view);
            dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onError() {

    }

    @Override
    protected void fetchData() {
        AdPresenter adPresenter = new AdPresenter(this);
        addPresenter(adPresenter);
        adPresenter.getAd();
    }
}
