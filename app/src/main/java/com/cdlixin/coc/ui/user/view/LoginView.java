package com.cdlixin.coc.ui.user.view;

/**
 * Created by 蒲弘宇 on 2017/10/9.
 */

public interface LoginView {
    void showToast(String string);
    void countDown();
    void checkPhoneNumIng();
    void checkPhoneFaile();
    void LoginSuccess();
}
