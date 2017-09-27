package com.cdlixin.coc.ui.news;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.presenter.main.impl.MainActivityPresenter;

/**
 * A simple {@link Fragment} subclass.
 * 复用的资讯列表页
 */
public class NewsFragment extends BaseFrament {

    @Override
    public int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

}
