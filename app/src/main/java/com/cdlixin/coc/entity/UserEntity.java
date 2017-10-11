package com.cdlixin.coc.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class UserEntity {

    /**
     * orgs : [{"c_id":"3e43d1d976b9430c8de0975394ff7007","c_type":0,"c_name":"商会之家开发商会（测试）","c_logo_url":"http://mobile.shzj168.cn/mobile//UploadFiles/2017-05/186363069923061573599889672.png","u_type":3}]
     * s_token : 16f0f0590d65496987e97dcd75c4ef9c
     * u_id : f7dc203317fe4103bb2b34565143b2e7
     * u_name : 蒲弘宇
     * u_logo_url : http://mobile.shzj168.cn/UserLogos/f7dc203317fe4103bb2b34565143b2e7.jpeg
     * u_post : 程序员
     * u_male : true
     */

    private String s_token;
    private String u_id;
    private String u_name;
    private String u_logo_url;
    private String u_post;
    private boolean u_male;
    private List<OrgEntity> orgs;

    public String getS_token() {
        return s_token;
    }

    public void setS_token(String s_token) {
        this.s_token = s_token;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_logo_url() {
        return u_logo_url;
    }

    public void setU_logo_url(String u_logo_url) {
        this.u_logo_url = u_logo_url;
    }

    public String getU_post() {
        return u_post;
    }

    public void setU_post(String u_post) {
        this.u_post = u_post;
    }

    public boolean isU_male() {
        return u_male;
    }

    public void setU_male(boolean u_male) {
        this.u_male = u_male;
    }

    public List<OrgEntity> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<OrgEntity> orgs) {
        this.orgs = orgs;
    }

}
