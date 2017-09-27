package com.cdlixin.coc.ui.main.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.presenter.main.impl.MainActivityPresenter;
import com.cdlixin.coc.ui.main.view.MainView;
import com.cdlixin.coc.ui.main.widget.BottomTabBar;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView{

    @Bind(R.id.replace)
    FrameLayout replace;
    @Bind(R.id.bottomTabBar)
    BottomTabBar bottomTabBar;


    private MainActivityPresenter mPresenter = (MainActivityPresenter) getPresenter();

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
        mPresenter.initFragments(this);
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
                mPresenter.showFragment(0);
            }

            @Override
            public void clickTabTwo() {
                mPresenter.showFragment(1);
            }

            @Override
            public void clickTabThree() {
                mPresenter.showFragment(2);
            }

            @Override
            public void clickTabFour() {
                mPresenter.showFragment(3);
            }

            @Override
            public void clickTabFive() {
                mPresenter.showFragment(4);
            }
        });
    }

}
