package com.cdlixin.coc.model.network;

import com.cdlixin.coc.global.constants.Url;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 请求管理器 - 初始化retrofit
 */

public class RequestManger {

    /**
     * http请求生成服务
     */
    private static HttpService service;
    private static final int OUTTIME = 5;


    public RequestManger(String baseUrl) {

        OkHttpClient client = new OkHttpClient.Builder()
                // 连接超时时间设置
                .connectTimeout(OUTTIME, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(OUTTIME, TimeUnit.SECONDS)
                // 失败重试
                .retryOnConnectionFailure(true)
                // 使用host name作为cookie保存的key
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()) //使用工厂模式创建字符串的解析器
//                .addConverterFactory(GsonConverterFactory.create()) //使用工厂模式创建Gson的解析器
                .build();
        service = retrofit.create(HttpService.class);
    }

    /**
     * 得到请求生成服务（请求生成器）
     * @return
     */
    public  HttpService getService() {
        return service;
    }
}
