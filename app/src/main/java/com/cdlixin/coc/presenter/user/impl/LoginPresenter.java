package com.cdlixin.coc.presenter.user.impl;

import android.os.Message;

import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.model.impl.LoginModel;
import com.cdlixin.coc.presenter.user.LoginToDo;
import com.cdlixin.coc.ui.user.view.LoginView;
import com.cdlixin.coc.utils.DeviceManager;
import com.cdlixin.coc.utils.GsonUtil;
import com.cdlixin.coc.utils.LogUtil;
import com.cdlixin.coc.utils.NetWorkUtil;
import com.google.gson.reflect.TypeToken;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by 蒲弘宇 on 2017/10/9.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginToDo{

    private LoginView loginView;
    private LoginModel loginModel;
    private String simNumber;

    public LoginPresenter(BaseActivity context) {
        super(context);
        loginView = getView();
        loginModel = LoginModel.getInstance();
    }

    @Override
    public void checkPhone(final String phoneNumber) {
        final Message msg = new Message();
        Subscriber<String> sb =new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String string) {
                if(DEBUG){
                    showLog("检查电话号码~~~~~"+string);
                }
                Result result = GsonUtil.GsonToBean(string,new TypeToken<Result>(){}.getType());
                //手机号码检查成功 请求验证码
                if(result.getResponse_code() == 0){
                    //设置倒计时让用户不能连续获取验证码按钮

                    //获取验证码
                    getSmsVerifyCode(phoneNumber);
                } else if(result.getResponse_code() == 200){
                    //提示用户选择商会进行加入
                    loginView.showToast(result.getResponse_msg());
                }else if(result.getResponse_code() == 210) {
                    loginView.showToast(result.getResponse_msg());
                }else {
                    loginView.showToast(result.getResponse_msg());
                }
            }
        };
        if(NetWorkUtil.isNetworkConnected()){
            loginModel.check_mobile(phoneNumber,sb);
        }else {
            loginView.showToast("网络连接失败");
        }

    }

    @Override
    public void getSmsVerifyCode(String phoneNumber) {
        simNumber = DeviceManager.getSIMNumber();
        Subscriber<String> sb = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String string) {
                Result result = GsonUtil.GsonToBean(string,new TypeToken<Result>(){}.getType());
                if(DEBUG) {
                    showLog("获取验证码~~~~~"+result.toString());
                }
            }
        };

        if(NetWorkUtil.isNetworkConnected()){
            loginModel.sms_verify(phoneNumber,simNumber,sb);
        }else {
            loginView.showToast("网络连接失败");
        }
    }
}
