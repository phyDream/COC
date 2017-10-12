package com.cdlixin.coc.presenter.news.impl;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.model.impl.ChannelModel;
import com.cdlixin.coc.ui.news.view.ChannelManagerView;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/10/12.
 */

public class ChannelManagerPresenter extends BasePresenter<ChannelManagerView>{
    private ChannelManagerView channelManagerView;
    private ChannelModel channelModel;
    public ChannelManagerPresenter(BaseActivity context) {
        super(context);
        channelManagerView = (ChannelManagerView) context;
        channelModel = ChannelModel.getInstance();
    }

    //获取我的频道
    public void getMyChannels(){
//        return channelModel.getChannels();
    }

    //获取其他频道
    public List<ChannelItem> getOtherChannels(){
        return null;
    }
}
