package com.cdlixin.coc.global;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;

import com.cdlixin.coc.model.daohelper.DataBaseManager;
import com.cdlixin.coc.service.NetworkStateService;
import com.cdlixin.coc.utils.AppUtil;
import com.cdlixin.coc.utils.LogUtil;
import com.cdlixin.coc.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by moonfollower on 2016/10/26.
 */

public class MyApplication extends Application{

    /**
     * 记录所有活动的Activity
     */
    public static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    //是否处于调试模式
    public static final boolean DEBUG = true;
    //用户是否登陆
    public static boolean isUserLogin = false;

    /**
     * 所有资讯每页展示数量
     */
    public static final int NEWS_COUNT = 20;

    private static MyApplication myApp = null;


    //默认主进程包名
    public static final String REAL_PACKAGE_NAME = "com.cdlixin.coc";


    /**
     * 网络状态服务
     */
    private Service mNetStateService = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //进程名
        String processName = AppUtil.getProcessName(this, android.os.Process.myPid());
        if (processName != null) {//进程名不为空
            boolean defaultProcess = processName.equals(REAL_PACKAGE_NAME);
            if (defaultProcess) {//在默认进程才执行（防止后台进程重复调用onCreate()）
                initAppForMainProcess();
            }
        }

    }

    //默认进程中执行的初始化操作
    private void initAppForMainProcess(){
        LogUtil.i("~APP初始化~");
        //共享参数初始化
        SharedPreferencesHelper.init(this);
        //数据库管理器初始化
        DataBaseManager.init(this);
        //对外提供app对象
        myApp = this;
        //网络状态监听服务
        bindNetWorkStateService();

    }


    /**
     * 绑定网络状态监听服务
     */
    private void bindNetWorkStateService(){
        Intent intent = new Intent(this,NetworkStateService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 获取APP上下文
     * @return
     */
    public static Context getAppContext(){
        return myApp.getApplicationContext();
    }

    /**
     * bindService connection
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            NetworkStateService.NetworkStateBinder binder = (NetworkStateService.NetworkStateBinder) service;
            mNetStateService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mNetStateService = null;
        }
    };

    public static String geResStr(int id){
        return getAppContext().getResources().getString(id);
    }

}
