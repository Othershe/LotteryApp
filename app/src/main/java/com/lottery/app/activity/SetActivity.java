package com.lottery.app.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;


import com.lottery.app.R;

import butterknife.BindView;

public class SetActivity extends BaseActivity {
    @BindView(R.id.set_toolbar)
    Toolbar mToolbar;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("设置");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
