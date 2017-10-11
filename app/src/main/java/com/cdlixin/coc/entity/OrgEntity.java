package com.cdlixin.coc.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

@Entity
public class OrgEntity {
    /**
     * c_id : 3e43d1d976b9430c8de0975394ff7007
     * c_type : 0
     * c_name : 商会之家开发商会（测试）
     * c_logo_url : http://mobile.shzj168.cn/mobile//UploadFiles/2017-05/186363069923061573599889672.png
     * u_type : 3
     */

    @Id(autoincrement = true)
    private Long tableId;
    /**
     * 用户id
     */
    private Long userId;
    private String c_id;
    private int c_type;
    private String c_name;
    private String c_logo_url;
    private int u_type;

    @Generated(hash = 1233279454)
    public OrgEntity(Long tableId, Long userId, String c_id, int c_type, String c_name,
            String c_logo_url, int u_type) {
        this.tableId = tableId;
        this.userId = userId;
        this.c_id = c_id;
        this.c_type = c_type;
        this.c_name = c_name;
        this.c_logo_url = c_logo_url;
        this.u_type = u_type;
    }

    @Generated(hash = 2061818262)
    public OrgEntity() {
    }
    public Long getTableId() {
        return this.tableId;
    }
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getC_id() {
        return this.c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public int getC_type() {
        return this.c_type;
    }
    public void setC_type(int c_type) {
        this.c_type = c_type;
    }
    public String getC_name() {
        return this.c_name;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
    public String getC_logo_url() {
        return this.c_logo_url;
    }
    public void setC_logo_url(String c_logo_url) {
        this.c_logo_url = c_logo_url;
    }
    public int getU_type() {
        return this.u_type;
    }
    public void setU_type(int u_type) {
        this.u_type = u_type;
    }

    @Override
    public String toString() {
        return "OrgEntity{" +
                "tableId=" + tableId +
                ", userId=" + userId +
                ", c_id='" + c_id + '\'' +
                ", c_type=" + c_type +
                ", c_name='" + c_name + '\'' +
                ", c_logo_url='" + c_logo_url + '\'' +
                ", u_type=" + u_type +
                '}';
    }
}
