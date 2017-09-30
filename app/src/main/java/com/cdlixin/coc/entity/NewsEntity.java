package com.cdlixin.coc.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 蒲弘宇 on 2017/9/30.
 */

@Entity
public class NewsEntity {
    /**
     * 表内主键
     *
     *
     infos	array	商会资讯数组
     info_id	string	资讯ID
     info_pic_url	string	标题配图的URL地址
     info_title	string	标题
     info_sum	string	摘要信息
     info_date	date	发布时间
     info_owner	string	发布者名称
     info_source	string	来源
     Info_text	string	正文页面url地址
     *
     */

    /**
     * 广告或者新闻
     */
    @Transient
    private int type;
    /**
     * 资讯id
     */
    private String info_id;
    /**
     * 配图地址
     */
    private String info_pic_url;
    /**
     * 资讯标题
     */
    private String info_title;
    /**
     * 摘要信息
     */
    private String info_sum;
    /**
     * 发布时间
     */
    private String info_date;
    /**
     * 发布者名称
     */
    private String info_owner;
    /**
     * 信息来源
     */
    private String info_source;
    /**
     * 资讯正文Url地址
     */
    private String info_text;
    /**
     * 所在频道
     */
    private String channelId;

    /**
     * 预留字段
     */
    private String whatEver;

    @Generated(hash = 1722007861)
    public NewsEntity(String info_id, String info_pic_url, String info_title,
            String info_sum, String info_date, String info_owner,
            String info_source, String info_text, String channelId,
            String whatEver) {
        this.info_id = info_id;
        this.info_pic_url = info_pic_url;
        this.info_title = info_title;
        this.info_sum = info_sum;
        this.info_date = info_date;
        this.info_owner = info_owner;
        this.info_source = info_source;
        this.info_text = info_text;
        this.channelId = channelId;
        this.whatEver = whatEver;
    }

    @Generated(hash = 2121778047)
    public NewsEntity() {
    }

    public String getInfo_id() {
        return this.info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getInfo_pic_url() {
        return this.info_pic_url;
    }

    public void setInfo_pic_url(String info_pic_url) {
        this.info_pic_url = info_pic_url;
    }

    public String getInfo_title() {
        return this.info_title;
    }

    public void setInfo_title(String info_title) {
        this.info_title = info_title;
    }

    public String getInfo_sum() {
        return this.info_sum;
    }

    public void setInfo_sum(String info_sum) {
        this.info_sum = info_sum;
    }

    public String getInfo_date() {
        return this.info_date;
    }

    public void setInfo_date(String info_date) {
        this.info_date = info_date;
    }

    public String getInfo_owner() {
        return this.info_owner;
    }

    public void setInfo_owner(String info_owner) {
        this.info_owner = info_owner;
    }

    public String getInfo_source() {
        return this.info_source;
    }

    public void setInfo_source(String info_source) {
        this.info_source = info_source;
    }

    public String getInfo_text() {
        return this.info_text;
    }

    public void setInfo_text(String info_text) {
        this.info_text = info_text;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getWhatEver() {
        return this.whatEver;
    }

    public void setWhatEver(String whatEver) {
        this.whatEver = whatEver;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "type=" + type +
                ", info_id='" + info_id + '\'' +
                ", info_pic_url='" + info_pic_url + '\'' +
                ", info_title='" + info_title + '\'' +
                ", info_sum='" + info_sum + '\'' +
                ", info_date='" + info_date + '\'' +
                ", info_owner='" + info_owner + '\'' +
                ", info_source='" + info_source + '\'' +
                ", info_text='" + info_text + '\'' +
                ", channelId='" + channelId + '\'' +
                ", whatEver='" + whatEver + '\'' +
                '}';
    }
}
