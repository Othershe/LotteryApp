package app.lottery.com.lotteryapp;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.lottery.com.lotteryapp.activity.BaseActivity;
import app.lottery.com.lotteryapp.adapter.TypePageAdapter;
import app.lottery.com.lotteryapp.fragment.BaseMvpFragment;
import app.lottery.com.lotteryapp.fragment.NewsFragment;
import app.lottery.com.lotteryapp.fragment.ResultsFragment;
import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.title)
    TextView mTitle;

    private int lastPos = -1;
    private int currentPos = 0;

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
                }
            }
        });
    }

    @Override
    protected void initData() {
        List<BaseMvpFragment> fragments = new ArrayList<>();
        fragments.add(NewsFragment.newInstance());
        fragments.add(ResultsFragment.newInstance());

        TypePageAdapter adapter = new TypePageAdapter(getSupportFragmentManager());
        adapter.setData(fragments);

        mViewPager.setAdapter(adapter);
    }

    private void changeStatus() {
        if (currentPos == 0) {
            mTitle.setText("彩票资讯");
        } else if (currentPos == 1) {
            mTitle.setText("双色球开奖结果");
        }
        ((RadioButton) mRadioGroup.getChildAt(lastPos)).setTextColor(Color.parseColor("#999999"));
        ((RadioButton) mRadioGroup.getChildAt(currentPos)).setChecked(true);
        ((RadioButton) mRadioGroup.getChildAt(currentPos))
                .setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
