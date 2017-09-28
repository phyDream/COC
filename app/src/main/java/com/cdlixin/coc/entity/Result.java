package com.cdlixin.coc.entity;

/**
 * Created by phy on 2016/11/30.
 */

public class Result<T>{

    /**
     * response_id : 20161130105822796
     * response_code : 0
     * response_msg :
     * response_data : {"s_token":"4665b2680b204035a49aaadc46c24ef0","c_id":"827137801e894aaaae10141301c47a58","c_name":"成都立鑫新技术科技有限公司","c_region":"四川省 成都市 武侯区 四川省 成都市 武侯区","c_industry":"","c_addr":"成都市武侯区武青南路33号B栋302","c_business":"","c_phone":"12345678","u_id":"2b86ca0206e24814b7687f7e7cf1263a","u_name":"企业测试3","u_post":"测试员","u_type":0,"u_male":false}
     */

    private String response_id;
    private int response_code;
    private String response_msg;
    private T response_data;

    public String getResponse_id() {
        return response_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public void setResponse_msg(String response_msg) {
        this.response_msg = response_msg;
    }

    public T getResponse_data() {
        return response_data;
    }

    public void setResponse_data(T response_data) {
        this.response_data = response_data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "response_id='" + response_id + '\'' +
                ", response_code=" + response_code +
                ", response_msg='" + response_msg + '\'' +
                ", response_data=" + response_data +
                '}';
    }

}
