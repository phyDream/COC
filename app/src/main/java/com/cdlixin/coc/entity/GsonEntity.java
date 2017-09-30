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

    private List<NewsEntity> news;


    public List<ChannelItem> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelItem> channels) {
        this.channels = channels;
    }

    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }
}
