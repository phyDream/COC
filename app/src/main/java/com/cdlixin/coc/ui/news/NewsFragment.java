package com.cdlixin.coc.ui.news;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.presenter.news.impl.NewsListPresenter;
import com.cdlixin.coc.ui.main.view.NewsListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 复用的资讯列表页
 */
public class NewsFragment extends BaseFrament<NewsListView, NewsListPresenter> implements NewsListView {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.tv_default)
    TextView tvDefault;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

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
    public void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    protected NewsListPresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
