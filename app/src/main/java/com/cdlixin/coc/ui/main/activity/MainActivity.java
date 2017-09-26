package com.cdlixin.coc.ui.main.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.presenter.impl.MainActivityPresenter;
import com.cdlixin.coc.ui.main.fragment.FourFragment;
import com.cdlixin.coc.ui.main.fragment.OneFragment;
import com.cdlixin.coc.ui.main.fragment.ThreeFragment;
import com.cdlixin.coc.ui.main.fragment.TwoFragment;
import com.cdlixin.coc.ui.main.view.MainView;
import com.cdlixin.coc.ui.main.widget.BottomTabBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView{

    @Bind(R.id.replace)
    FrameLayout replace;
    @Bind(R.id.bottomTabBar)
    BottomTabBar bottomTabBar;

    private List<BaseFrament> framents = new ArrayList<>();
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

    }

    @Override
    protected void initData() {
        framents.add(new OneFragment());
        framents.add(new TwoFragment());
        framents.add(new ThreeFragment());
        framents.add(new FourFragment());
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
                mPresenter.switchOne();
            }

            @Override
            public void clickTabTwo() {
                mPresenter.switchOne();
            }

            @Override
            public void clickTabThree() {
                mPresenter.switchOne();
            }

            @Override
            public void clickTabFour() {
                mPresenter.switchOne();
            }
        });
    }

    @Override
    public void switchOne() {

    }

    @Override
    public void switchTwo() {

    }

    @Override
    public void switchThree() {

    }

    @Override
    public void switchFour() {

    }
}
