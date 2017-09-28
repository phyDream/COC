package com.cdlixin.coc.entity;

import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/28.
 */

public class GsonEntity {
    /**
     * 频道列表
     */
    private List<ChannelItem> channels;

    public List<ChannelItem> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelItem> channels) {
        this.channels = channels;
    }
}
