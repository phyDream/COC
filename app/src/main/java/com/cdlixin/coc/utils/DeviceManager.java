package com.cdlixin.coc.utils;

import android.content.Context;
import android.telephony.TelephonyManager;


import com.cdlixin.coc.global.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 设备信息管理器
 */

public class DeviceManager {

    //获取手机IMEI-UserId 6.0以上需要用户同意权限 android.permission.READ_PHONE_STATE。用的是PermissionGen库完成

    public static String getPhoneId(){
        TelephonyManager telephoneManager = (TelephonyManager) MyApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephoneManager.getDeviceId();
    }

    //读取用户手机卡序列号
    public static String getSIMNumber(){
        TelephonyManager telephoneManager = (TelephonyManager) MyApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephoneManager.getSimSerialNumber();
    }

    /**
     * sim卡是否准备好
     * @return
     */
    public static boolean isSimReady(){
        TelephonyManager telephoneManager = (TelephonyManager) MyApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        try{
            if(telephoneManager.getSimState() == TelephonyManager.SIM_STATE_READY){
                return true;
            }
        }catch (Exception e){

        }

        return false;
    }


    /**
     * 获取系统当前时间
     * @return 时间格式的字符串
     */
    public static String getSystemTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    
}
