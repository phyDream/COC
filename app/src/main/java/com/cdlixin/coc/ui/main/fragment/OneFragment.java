package com.cdlixin.coc.ui.main.fragment;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.cdlixin.coc.R;
import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.presenter.main.impl.NewsPresenter;
import com.cdlixin.coc.ui.main.adapter.NewsPageAdapter;
import com.cdlixin.coc.ui.main.view.NewsPageView;
import com.cdlixin.coc.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends BaseFrament<NewsPageView,NewsPresenter> implements NewsPageView{

    @Bind(R.id.TopBar)
    com.cdlixin.coc.ui.main.widget.TopBar TopBar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;


    private NewsPresenter presenter;
    private NewsPageAdapter adapter;
    private TabLayout tabLayout;
    private List<ChannelItem> channels = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.fragment_one;
    }

    @Override
    public void initView(View view) {
        DEBUG = false;
        presenter = (NewsPresenter) mPresenter;
        presenter.initChannels();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @Override
    public void setListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected NewsPresenter getPresenter() {
        return new NewsPresenter((BaseActivity) getActivity(),this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showChannel(List<ChannelItem> channelItems) {
        channels.addAll(channelItems);
        adapter = new NewsPageAdapter(getChildFragmentManager(),channels);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        adapter.notifyDataSetChanged();
        tabLayout = TopBar.getTabLayout();
        //为TabLayout设置ViewPager
        tabLayout.setupWithViewPager(viewPager);
        showLog("~展示的频道列表~"+channelItems.size()+"~"+channelItems);
    }

    @Override
    public void showToast(String string) {
        ToastUtils.showToast(getContext(),string);
    }


}
