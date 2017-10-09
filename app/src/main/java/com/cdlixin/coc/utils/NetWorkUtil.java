package com.cdlixin.coc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cdlixin.coc.global.MyApplication;

import static com.cdlixin.coc.utils.NetWorkUtil.NetType.CMNET;


/**
 * 网络工具类
 *
 * @author Administrator
 *
 */
public class NetWorkUtil {

    /**
     * 网络请求成功标记码
     */
    public static final int NETWORK_SUCCEED = 0;

    /**
     * 有网但是请求服务器失败时。
     */
    public static final int CLIENT_FAILED = -2;
    /**
     * 无网络标志
     */
    public static final int NETWORK_FAILED = -1;


    private static final Context context = MyApplication.getAppContext();

    // 网络状态，连接wifi，cmnet是直连互联网的，cmwap是需要代理，noneNet是无连接的
    // 一速度来说：wifi > cmnet >cmwap > noneNet
    public enum NetType
    {
        wifi, CMNET, CMWAP, noneNet
    }

    /**
     * 网络是否可用
     *
     * @return
     */
    public static boolean isNetworkAvailable()
    {
        // 获取网络manager
        ConnectivityManager mgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();

        // 遍历所有可以连接的网络
        if (info != null)
        {
            for (int i = 0; i < info.length; i++)
            {
                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断是否有网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected()
    {
        if (context!= null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null)
            {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @return
     */
    public static boolean isWifiConnected()
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetInfo = mConnectivityManager.getActiveNetworkInfo();
            if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI)
            {
                return true;
            }
        }
        return false;



    }
    /**
     * 判断MOBILE网络是否可用
     *
     * @return
     */
    public static boolean isMobileConnected()
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null)
            {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息
     *
     * @return
     */
    public static int getConnectedType()
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager)context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable())
            {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     *
     *获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
     *
     *
     * @return
     */
    public static NetType getAPNType()
    {
        ConnectivityManager connMgr = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null)
        {
            return NetType.noneNet;
        }
        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_MOBILE)
        {
            if (networkInfo.getExtraInfo().toLowerCase().equals(CMNET))
            {
                return CMNET;
            }

            else
            {
                return NetType.CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI)
        {
            return NetType.wifi;
        }
        return NetType.noneNet;

    }

}