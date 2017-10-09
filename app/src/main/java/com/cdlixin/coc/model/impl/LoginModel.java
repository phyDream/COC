package com.cdlixin.coc.model.impl;

import com.cdlixin.coc.global.constants.Url;
import com.cdlixin.coc.model.dao.MyNewsDao;
import com.cdlixin.coc.model.network.HttpService;
import com.cdlixin.coc.model.network.RequestManger;
import com.cdlixin.coc.utils.SignatureUtil;
import com.cdlixin.coc.utils.TimeUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蒲弘宇 on 2017/10/9.
 */

public class LoginModel {

    //请求生成器
    private HttpService service = new RequestManger(Url.BASE_URL).getService();
    private static LoginModel loginModel = null;
    //是否处于调试模式
    public boolean DEBUG = true;

    public static LoginModel getInstance(){
        if(loginModel == null){
            synchronized (NewsModel.class){
                if(loginModel == null){
                    loginModel = new LoginModel();
                }
            }
        }
        return loginModel;
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
}
