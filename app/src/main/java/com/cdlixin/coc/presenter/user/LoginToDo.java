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
}
