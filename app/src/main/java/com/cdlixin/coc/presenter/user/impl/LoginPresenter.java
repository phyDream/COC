package com.cdlixin.coc.presenter.user.impl;


import com.cdlixin.coc.R;
import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.entity.UserEntity;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.global.MyApplication;
import com.cdlixin.coc.model.impl.UserModel;
import com.cdlixin.coc.presenter.user.LoginToDo;
import com.cdlixin.coc.ui.user.view.LoginView;
import com.cdlixin.coc.utils.DeviceManager;
import com.cdlixin.coc.utils.GsonUtil;
import com.cdlixin.coc.utils.NetWorkUtil;
import com.cdlixin.coc.utils.TimeUtil;
import com.google.gson.reflect.TypeToken;

import rx.Subscriber;

/**
 * Created by 蒲弘宇 on 2017/10/9.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginToDo{

    private LoginView loginView;
    private UserModel userModel;
    private String simNumber;

    public LoginPresenter(BaseActivity context) {
        super(context);
        loginView = (LoginView) context;;
        userModel = UserModel.getInstance();
    }

    @Override
    public void checkPhone(final String phoneNumber) {
        Subscriber<String> sb =new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String string) {
                Result result = GsonUtil.GsonToBean(string,new TypeToken<Result>(){}.getType());
                showLog("检查电话号码~~~~~"+string);
                //手机号码检查成功 请求验证码
                if(result.getResponse_code() == 0){
                    //设置倒计时让用户不能连续获取验证码按钮
                    loginView.countDown();
                    //获取验证码
                    getSmsVerifyCode(phoneNumber);
                } else if(result.getResponse_code() == 200){
                    //提示用户选择商会进行加入
                    //界面刷新
                    loginView.checkPhoneFaile();
                }else if(result.getResponse_code() == 210) {
                    //界面刷新
                    loginView.checkPhoneFaile();
                }
                loginView.showToast(result.getResponse_msg());
            }
        };
        if(NetWorkUtil.isNetworkConnected()){
            //得到最近一次获取验证码时间
            int getLastCodeTime = userModel.getCodeTime();
            int currTime = TimeUtil.getCurrentTimes();
            int time = currTime-getLastCodeTime;
            if(time <= 60){
                loginView.showToast(String.format(MyApplication.geResStr(R.string.The_time_is_too_short),60 - time));
            }else{
                loginView.checkPhoneNumIng();
                //检查手机号
                userModel.check_mobile(phoneNumber,sb);
                //存入该手机号
                userModel.savePhoneNum(phoneNumber);
            }

        }else {
            loginView.showToast(MyApplication.geResStr(R.string.Network_connection_failed));
        }

    }

    @Override
    public void getSmsVerifyCode(String phoneNumber) {
        showLog("获取验证码请求~~~~~");
        simNumber = DeviceManager.getSIMNumber();
        Subscriber<String> sb = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                showLog("获取验证码完成~~~~~");
            }
            @Override
            public void onError(Throwable e) {
                showLog("获取验证码失败~~~~~");
            }
            @Override
            public void onNext(String string) {
                Result result = GsonUtil.GsonToBean(string,new TypeToken<Result>(){}.getType());
                showLog("获取验证码~~~~~"+result.getResponse_msg());
                if(result.getResponse_msg() != null) {
                    loginView.showToast(result.getResponse_msg());
                    userModel.saveCodeTime();
                }
            }
        };

        if(NetWorkUtil.isNetworkConnected()){
            userModel.sms_verify(phoneNumber,simNumber,sb);
        }else {
            loginView.showToast(MyApplication.geResStr(R.string.Network_connection_failed));
        }
    }

    @Override
    public String getPhoneNum() {
        return userModel.getPhoneNum();
    }

    @Override
    public void deviceRegister(final String deviceId, final String uid, final String phoneNub, final String verifyCode) {
        final Subscriber sb = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String string) {
                Result result = GsonUtil.GsonToBean(string,new TypeToken<Result>(){}.getType());
                showLog("设备注册~~"+string);
                showLog("账户验证~~"+"deviceId~"+deviceId+"simNumber~"+simNumber);
                if(result.getResponse_code() == 0){
                    verify(10,deviceId,simNumber,"");
                }
                loginView.showToast(result.getResponse_msg());
            }
        };

        if(NetWorkUtil.isNetworkConnected()){
            showLog("设备注册请求~~"+"deviceId~"+deviceId+"simNumber~"+simNumber+"phoneNub~"+phoneNub+simNumber+"verifyCode~"+verifyCode);
            userModel.register(deviceId,simNumber,phoneNub,1,verifyCode,sb);
        }else {
            loginView.showToast(MyApplication.geResStr(R.string.Network_connection_failed));
        }
    }

    @Override
    public void verify(int auth_mode, String deviceId, String userId, String password) {
        Subscriber<UserEntity> sb = new Subscriber<UserEntity>() {
            @Override
            public void onCompleted() {
                showLog("身份验证完成~");
            }
            @Override
            public void onError(Throwable e) {
                showLog("身份验证失败~");
            }
            @Override
            public void onNext(UserEntity userEntity) {
                showLog("用户账号信息~"+userEntity);
            }
        };
        if(NetWorkUtil.isNetworkAvailable()){
            showLog("身份验证请求~");
            userModel.verify(10,deviceId,userId,"",sb);
        }else {
            loginView.showToast(MyApplication.geResStr(R.string.Network_connection_failed));
        }

    }
}
