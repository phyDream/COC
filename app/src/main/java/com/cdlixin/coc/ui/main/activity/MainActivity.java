package com.cdlixin.coc.ui.main.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.presenter.impl.MainActivityPresenter;
import com.cdlixin.coc.ui.main.widget.BottomTabBar;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.replace)
    FrameLayout replace;
    @Bind(R.id.bottomTabBar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return new MainActivityPresenter(this);
    }

    @Override
    protected void setListener() {

        bottomTabBar.setTabsClickListener(new BottomTabBar.TabsClickListener() {
            @Override
            public void clickTabOne() {

            }

            @Override
            public void clickTabTwo() {

            }

            @Override
            public void clickTabThree() {

            }

            @Override
            public void clickTabFour() {

            }
        });
    }
}
