package com.cdlixin.coc.ui.main.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.ui.news.NewsFragment;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class NewsPageAdapter extends FragmentStatePagerAdapter {

    private List<ChannelItem> channelItems;

    public NewsPageAdapter(FragmentManager fm, List<ChannelItem> channelItems) {
        super(fm);
        this.channelItems = channelItems;
    }

    @Override
    public Fragment getItem(int position) {
        ChannelItem channel = channelItems.get(position);
        Fragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",channel.getId());
        fragment.setArguments(bundle);
        return null;
    }

    @Override
    public int getCount() {
        return channelItems.size();
    }

    //获取头
    @Override
    public CharSequence getPageTitle(int position) {
        if(null != channelItems && channelItems.size() > 0){
            return channelItems.get(position).getName();
        }
        else return null;
    }
}
