package com.cdlixin.coc.model.impl;

import android.text.TextUtils;

import com.cdlixin.coc.entity.OrgEntity;
import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.entity.UserEntity;
import com.cdlixin.coc.global.constants.SpKey;
import com.cdlixin.coc.global.constants.Url;
import com.cdlixin.coc.model.dao.MyOrgdao;
import com.cdlixin.coc.model.dao.MyUserDao;
import com.cdlixin.coc.model.network.HttpService;
import com.cdlixin.coc.model.network.RequestManger;
import com.cdlixin.coc.utils.GsonUtil;
import com.cdlixin.coc.utils.LogUtil;
import com.cdlixin.coc.utils.SharedPreferencesHelper;
import com.cdlixin.coc.utils.SignatureUtil;
import com.cdlixin.coc.utils.TimeUtil;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 蒲弘宇 on 2017/10/9.
 */

public class UserModel {

    //请求生成器
    private HttpService service = new RequestManger(Url.BASE_URL).getService();
    private static UserModel userModel = null;
    private MyUserDao dao = MyUserDao.getInstance();
    private MyOrgdao daoOrg = MyOrgdao.getInstance();

    //是否处于调试模式
    public boolean DEBUG = true;

    public static UserModel getInstance(){
        if(userModel == null){
            synchronized (NewsModel.class){
                if(userModel == null){
                    userModel = new UserModel();
                }
            }
        }
        return userModel;
    }

    /**
     * 得到app节点的签名字符串
     * @param map
     * @param isPost 请求方式是否为post
     * @return
     */
    private static String getAppSign(Map<String,String> map, boolean isPost){
        return SignatureUtil.getSign(Url.BASE_URL, Url.APP,map,isPost);
    }

    /**
     * 得到设备节点的签名字符串
     * @param map
     * @param isPost 请求方式是否为post
     * @return
     */
    private static String getDeviceSign(Map<String,String> map, boolean isPost){
        return SignatureUtil.getSign(Url.BASE_URL, Url.DEVICE,map,isPost);
    }

    /**
     * 得到账户节点的签名字符串
     * @param map
     * @param isPost 请求方式是否为post
     * @return
     */
    private static String getAccountSign(Map<String,String> map, boolean isPost){
        return SignatureUtil.getSign(Url.BASE_URL, Url.ACCOUNT,map,isPost);
    }

    /**
     * 手机号码检查
     * @param mobile 手机号码
     */
    public void check_mobile(String mobile, Subscriber<String> sb){
        //发起请求时的Unix时间戳，使用格林威治标准时间
        int time = (int) TimeUtil.getGMTUnixTimeByCalendar();
        //参数键值对
        Map<String,String> map = new LinkedHashMap<>();
        map.put("method","check_mobile");
        map.put("timestamp",time+"");
        map.put("mobile",mobile);

        Observable<String> call = service.check_mobile("check_mobile",time,mobile,getAccountSign(map,true));
        Subscription subscription = call //note2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sb);
    }

    /**
     * 短信验证，输入手机号得到短信验证码
     * @param mobile
     * @param uid
     */
    public void sms_verify(String mobile,String uid,Subscriber<String> sb){
        //发起请求时的Unix时间戳，使用格林威治标准时间
        int time = (int) TimeUtil.getGMTUnixTimeByCalendar();
        //参数键值对
        Map<String,String> map = new LinkedHashMap<>();
        map.put("method","sms_verify");
        map.put("timestamp",time+"");
        map.put("mobile",mobile);
        map.put("uid",uid);
        Observable<String> call = service.sms_verify("sms_verify",time,mobile,uid,getDeviceSign(map,true));
        Subscription subscription = call //note2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sb);
    }

    /**
     * 存入默认手机号
     * @param phoneNum
     */
    public void savePhoneNum(String phoneNum){
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance();
        if(!TextUtils.isEmpty(phoneNum)){
            helper.saveData(SpKey.loginDefultPhone,phoneNum);
        }
    }

    /**
     * 得到默认手机号
     */
    public String getPhoneNum(){
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance();
        return (String) helper.getData(SpKey.loginDefultPhone,"");
    }

    /**
     * 存入获取验证码的请求时间
     */
    public void saveCodeTime( ){
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance();
        int time = TimeUtil.getCurrentTimes();
        helper.saveData(SpKey.getCodeTime,time);
    }

    /**
     * 得到默认手机号
     */
    public int getCodeTime(){
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance();
        return (int) helper.getData(SpKey.getCodeTime,0);
    }

    /**
     * 设备注册
     * @param mobileId 手机或移动设备的uid
     * @param uid 手机卡号id（可选）手机uid验证模式下，该参数为空--文档上面是手机卡序号
     * @param phoneNub 机主手机号
     * @param deviceType 移动设备类型 1：Andriod设备 2：iOS设备；3：Windows Phone设备；
     * @param verify_code 接收到的短信验证码
     */
    public void register(String mobileId,String uid, String phoneNub,int deviceType, String verify_code,Subscriber<String> sb){
        //发起请求时的Unix时间戳，使用格林威治标准时间
        int time = (int) TimeUtil.getGMTUnixTimeByCalendar();
        //参数键值对
        Map<String,String> map = new LinkedHashMap<>();
        map.put("method","register");
        map.put("timestamp",time+"");
        map.put("mobileId",mobileId);
        map.put("uid",uid);
        map.put("mobile",phoneNub);
        map.put("deviceType",deviceType+"");
        map.put("verify_code",verify_code);

        Observable<String> call = service.register("register",time,mobileId,uid,phoneNub,deviceType,verify_code,getDeviceSign(map,true));
        Subscription subscription = call //note2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sb);
    }

    /**
     * 身份验证
     * @param auth_mode 验证模式： (1) 10 - 手机uid验证； (2) 20 – 用户名/密码验证；
     * @param  mobileId  设备id
     * @param userId 登录用户名（可选）手机uid验证模式下，该参数为空。
     * @param pwd 密码（可选）；手机uid验证模式下，该参数为空。密码字符串使用ASCII方式编码后，采用SHA256进行加密。即：pwd=SHA256(ASCII(password))
     */
    public void verify(int auth_mode,String mobileId,String userId,String pwd,Subscriber<UserEntity> subscriber) {
        //发起请求时的Unix时间戳，使用格林威治标准时间
        int time = (int) TimeUtil.getGMTUnixTimeByCalendar();
        //参数键值对
        Map<String,String> map = new LinkedHashMap<>();
        map.put("method","verify");
        map.put("timestamp",time+"");
        map.put("auth_mode",auth_mode+"");
        map.put("mobileId",mobileId);
        map.put("uid",userId);
        map.put("pwd",pwd+"");

        Observable<String> call = service.verify("verify",time,auth_mode,mobileId,userId,pwd,getAccountSign(map,true));
        Subscription subscription = call //note2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, UserEntity>() {
                    @Override
                    public UserEntity call(String string) {
                        showLog("用户信息~"+string);
                        UserEntity user = null;
                        Result<UserEntity> userResult = GsonUtil.GsonToBean(string,new TypeToken<Result<UserEntity>>(){}.getType());
                        showLog("userResult~"+userResult);
                        if(userResult.getResponse_code() == 0){
                             user =  userResult.getResponse_data();
                            showLog("user~"+user);
                            //存入本地
                            if(user != null){
                                saveUserInfo(user);
                            }
                        }
                        return getUserInfo();
                    }
                })
                .subscribe(subscriber);
    }

    /**
     * 存入用户信息
     * @param userEntity
     */
    public void saveUserInfo(UserEntity userEntity){
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance();
        if(userEntity != null){
            //记录当前用户id
            helper.saveData(SpKey.currUserId,userEntity.getU_id());
            UserEntity newItem = userEntity;
            UserEntity oldItem = dao.selectById(newItem.getU_id() );
            //没有该用户，就存入
            if (oldItem == null) {//当前数据库中没有就插入
                dao.insert(newItem);
            }
        }

    }

    /**
     * 得到用户信息
     * @return
     */
    public UserEntity getUserInfo(){
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance();
        String userId = (String) helper.getData(SpKey.currUserId,"");
        showLog("userId~"+userId);
        UserEntity userEntity = null;
        if(userId != null){
            userEntity = dao.selectById(userId);
            showLog("来自数据库userEntity~"+userEntity);
        }
        return userEntity;
    }

    /**
     * 得到用户token
     * @return
     */
    public String getToken(){
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance();
        String userId = (String) helper.getData(SpKey.currUserId,"");
        String token = null;
        if(userId != null && !TextUtils.isEmpty(userId)){
            showLog("userId~"+userId);
            UserEntity userEntity = dao.selectById(userId);
            token = userEntity.getS_token();
            showLog("来自数据库token为~"+token);
        }
        return token;
    }

    //展示log
    protected void showLog(String string){
        if(DEBUG){
            LogUtil.i(string);
        }
    };

    //得到用户数
    public long getUserSize() {
        return dao.getSize();
    }

}
