package com.cdlixin.coc.presenter.impl;

import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.presenter.SwitchFragment;
import com.cdlixin.coc.ui.main.view.MainView;

/**
 * Created by 蒲弘宇 on 2017/9/26.
 */

public class MainActivityPresenter extends BasePresenter implements SwitchFragment {

    private MainView mainView;

    public MainActivityPresenter(BaseActivity context) {
        super(context);
        mainView = (MainView) context;
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
