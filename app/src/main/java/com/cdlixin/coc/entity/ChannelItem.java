package com.cdlixin.coc.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */
@Entity
public class ChannelItem {

    /**
     * 表内主键
     */
    @Id(autoincrement = true)
    private Long tableId;
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
    private boolean chose = false;

    @Generated(hash = 1842507087)
    public ChannelItem(Long tableId, String id, String name, Integer orderId,
            Integer selected, boolean chose) {
        this.tableId = tableId;
        this.id = id;
        this.name = name;
        this.orderId = orderId;
        this.selected = selected;
        this.chose = chose;
    }

    @Generated(hash = 1204777744)
    public ChannelItem() {
    }

    public Long getTableId() {
        return this.tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSelected() {
        return this.selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public boolean getChose() {
        return this.chose;
    }

    public void setChose(boolean chose) {
        this.chose = chose;
    }

    @Override
    public String toString() {
        return "ChannelItem{" +
                "tableId=" + tableId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", selected=" + selected +
                ", chose=" + chose +
                '}';
    }
}
