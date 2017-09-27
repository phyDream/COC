package com.cdlixin.coc.ui.main.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdlixin.coc.R;
import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.ui.main.adapter.NewsPageAdapter;
import com.cdlixin.coc.ui.main.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends BaseFrament {

    @Bind(R.id.TopBar)
    com.cdlixin.coc.ui.main.widget.TopBar TopBar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;


    private NewsPageAdapter adapter;
    private List<ChannelItem> channelItems;
    private TabLayout tabLayout;

    @Override
    public int bindLayout() {
        return R.layout.fragment_one;
    }

    @Override
    public void initView(View view) {

        adapter = new NewsPageAdapter(getChildFragmentManager(),channelItems);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        adapter.notifyDataSetChanged();
        tabLayout = TopBar.getTabLayout();
        //为TabLayout设置ViewPager
        tabLayout.setupWithViewPager(viewPager);
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
