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

    private List<NewsEntity> infos;

    public List<ChannelItem> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelItem> channels) {
        this.channels = channels;
    }

    public List<NewsEntity> getNews() {
        return infos;
    }

    public void setNews(List<NewsEntity> infos) {
        this.infos = infos;
    }

    @Override
    public String toString() {
        return "GsonEntity{" +
                "channels=" + channels +
                ", infos=" + infos +
                '}';
    }
}
