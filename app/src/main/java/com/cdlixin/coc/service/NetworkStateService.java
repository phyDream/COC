package com.cdlixin.coc.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdlixin.coc.utils.LogUtil;

/**
 * Created by moon on 2017/3/21.
 */

public class NetworkStateService extends Service {

    /**
     * 是否需要进行身份验证
     */
    public static boolean isNeedVerify = false;

    private ConnectivityManager mConnectivityManager = null;
    private NetworkInfo networkInfo = null;
    private final IBinder mBinder = new NetworkStateBinder();

    /**
     * binder
     */
    public class NetworkStateBinder extends Binder {
        public NetworkStateService getService() {
            // Return this instance of LocalService so clients can call public methods
            return NetworkStateService.this;
        }
    }

    /**
     * 广播接收器
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                LogUtil.e("网络状态发生了改变");
                mConnectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo = mConnectivityManager.getActiveNetworkInfo();
            }



        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}

