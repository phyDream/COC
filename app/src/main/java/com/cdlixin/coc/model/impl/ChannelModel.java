package com.cdlixin.coc.model.impl;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.model.IChannel;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class ChannelModel implements IChannel{
    private List<ChannelItem> channelItems;
    @Override
    public List<ChannelItem> getChannels() {

        return channelItems;
    }
}
