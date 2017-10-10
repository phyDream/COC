package com.cdlixin.coc.presenter.user;

/**
 * Created by 蒲弘宇 on 2017/10/9.
 */

public interface LoginToDo {
    /**
     * 服务端验证手机号
     * @param phoneNumber
     */
    void checkPhone(String phoneNumber);

    /**
     * 获取验证码
     * @param phoneNumber
     */
    void getSmsVerifyCode(String phoneNumber);

    /**
     * 得到默认手机号
     * @return
     */
    String getPhoneNum();

    /**
     * 设备注册
     * @return
     */
    void deviceRegister(String mobileUid,String uid, String phoneNub, String verifyCode);

    /**
     * 身份验证
     * @param mobileUuid  手机序列号
     * @param userId      用户名
     * @param password      密码
     */
    void verify(int auth_mode, String mobileUuid, final String userId, String password);
}
