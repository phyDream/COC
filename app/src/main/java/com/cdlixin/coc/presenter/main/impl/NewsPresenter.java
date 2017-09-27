package com.cdlixin.coc.presenter.main.impl;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.presenter.main.INewsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class NewsPresenter extends BasePresenter implements INewsPresenter{

    public NewsPresenter(BaseActivity context) {
        super(context);
    }

    @Override
    public List<ChannelItem> getChannels() {
        List<ChannelItem> channels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChannelItem c = new ChannelItem();
            c.setName("tab"+i);
            channels.add(c);
        }
        return channels;
    }
}
