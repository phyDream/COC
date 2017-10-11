package com.cdlixin.coc.ui.news.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cdlixin.coc.R;
import com.cdlixin.coc.entity.NewsEntity;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.constants.IntentKey;
import com.cdlixin.coc.presenter.news.impl.NewsListPresenter;
import com.cdlixin.coc.ui.news.adapter.NewsAdapter;
import com.cdlixin.coc.ui.news.view.NewsListView;
import com.cdlixin.coc.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 复用的资讯列表页
 */
public class NewsFragment extends BaseFrament<NewsListView, NewsListPresenter> implements NewsListView {

    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private int page = 1;

    private List<NewsEntity> newsList = new ArrayList<>();
    private NewsAdapter adapter = null;

    @Override
    public int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view) {
        DEBUG = true;
        adapter = new NewsAdapter(newsList,getActivity());
        //设置item放置模式
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {
        Bundle bundle =  getArguments();
        String id = bundle.getString(IntentKey.ChannelId);
        int channelId = Integer.valueOf(id);;
        showLog("~channelId~"+channelId);
        mPresenter.setNews(channelId,page);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1000);
            }
        });
    }

    @Override
    protected NewsListPresenter getPresenter() {
        return new NewsListPresenter((BaseActivity) getContext(),this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showNews(List<NewsEntity> newsEntities) {
//        showLog("~newsEntities~"+newsEntities);
        if(newsEntities != null && newsEntities.size() > 0){
            newsList.addAll(newsEntities);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showToast(String string) {
        ToastUtils.showToast(getContext(),string);
    }
}
