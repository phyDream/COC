package com.cdlixin.coc.entity;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class ChannelItem {
    /**
     * 频道id
     */
    private String id;
    /**
     * 频道名
     */
    private String name;
    /**
     * 频道顺序id
     */
    private Integer orderId;
    /**
     * 是否被选中 :1,选中；0 未选中
     */
    private Integer selected;

    /**
     * 是否被选中:tue，选中; false,未选中。
     */
    private boolean chose;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public boolean isChose() {
        return chose;
    }

    public void setChose(boolean chose) {
        this.chose = chose;
    }

    @Override
    public String toString() {
        return "ChannelItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", selected=" + selected +
                ", chose=" + chose +
                '}';
    }
}
