package com.cdlixin.coc.presenter.user.impl;


import com.cdlixin.coc.R;
import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.global.MyApplication;
import com.cdlixin.coc.model.impl.LoginModel;
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
    private LoginModel loginModel;
    private String simNumber;

    public LoginPresenter(BaseActivity context) {
        super(context);
        loginView = (LoginView) context;;
        loginModel = LoginModel.getInstance();
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
            int getLastCodeTime = loginModel.getCodeTime();
            int currTime = TimeUtil.getCurrentTimes();
            int time = currTime-getLastCodeTime;
            if(time <= 60){
                loginView.showToast(String.format(MyApplication.geResStr(R.string.The_time_is_too_short),60 - time));
            }else{
                loginView.checkPhoneNumIng();
                //检查手机号
                loginModel.check_mobile(phoneNumber,sb);
                //存入该手机号
                loginModel.savePhoneNum(phoneNumber);
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
                showLog("获取验证码~~~~~");
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
                    loginModel.saveCodeTime();
                }
            }
        };

        if(NetWorkUtil.isNetworkConnected()){
            loginModel.sms_verify(phoneNumber,simNumber,sb);
        }else {
            loginView.showToast(MyApplication.geResStr(R.string.Network_connection_failed));
        }
    }

    @Override
    public String getPhoneNum() {
        return loginModel.getPhoneNum();
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
                showLog("手机注册————"+string);
                if(result.getResponse_code() == 0){
                    verify(10,deviceId,simNumber,"");
                }

            }
        };

        if(NetWorkUtil.isNetworkConnected()){
            loginModel.register(deviceId,uid,phoneNub,1,verifyCode,sb);
        }else {
            loginView.showToast(MyApplication.geResStr(R.string.Network_connection_failed));
        }
    }

    @Override
    public void verify(int auth_mode, String deviceId, String userId, String password) {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {
                showLog("身份验证失败");
            }
            @Override
            public void onNext(String string) {
                showLog("用户账号信息~"+string);
                Result result = GsonUtil.GsonToBean(string,new TypeToken<Result>(){}.getType());
//                Result<UserEntity> userResult = GsonUtil.GsonToBean(string,new TypeToken<Result<UserEntity>>(){}.getType());

//                if(userResult.getResponse_code() == 0){
//
//                    MyApplication.isUserLogin = true;
//                    //用户登陆过
//                    LocalUserManager.getInstance().setIsUserLogin(true);
//                    //用户登陆状态
//                    LocalUserManager.getInstance().setLastTimeLoginState(true);
//
//                    UserEntity user =  userResult.getResponse_data();
//                    LogUtil.e("用户身份验证信息~"+string);
//
//                    /**
//                     * 发送广播更新界面
//                     */
//                    Intent intent = new Intent(IntentPutKey.ACTION_UPDATE_USER_FRAGMENT);
//                    MyApplication.getAppContext().sendBroadcast(intent);
//
//                    /**
//                     * 保存用户信息到本地
//                     */
//                    LocalUserManager.getInstance().saveUserBean(user);
//                    /**
//                     * 保存用户字符串
//                     */
//                    LocalUserManager.getInstance().saveUserJsonString(string);
//
//                    //登录成功绑定推送
//                    MyPushManager.bindPush(MyApplication.getAppContext(),user.getU_id());
//                }else {
//                    MyApplication.isUserLogin = false;
//                    LocalUserManager.getInstance().setLastTimeLoginState(false);
//                    LocalUserManager.getInstance().clearUserEntity();
//                }
            }
        };
//        if(NetWorkUtil.isNetworkAvailable()){
//            AccountNetworkManager.verify(auth_mode,mobileUuid,userId,password,subscriber);
//        }else {
//            msg.what = NetWorkUtil.NETWORK_FAILED;
//            if(null !=handler){
//                handler.sendMessage(msg);}
//        }
//        loginModel.verify(10,deviceId,"",sb);
    }
}
