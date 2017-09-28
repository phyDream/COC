package com.cdlixin.coc.model.impl;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.entity.GsonEntity;
import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.global.constants.Url;
import com.cdlixin.coc.model.network.HttpService;
import com.cdlixin.coc.model.network.RequestManger;
import com.cdlixin.coc.utils.GsonUtil;
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
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class ChannelModel {

    //请求生成器
    private static HttpService service = new RequestManger().getService();

    //对外提供单例
    private static ChannelModel channelModel = null;
    public static ChannelModel getInstance() {
        if (channelModel == null) {
            channelModel = new ChannelModel();
        }
        return channelModel;
    }

    /**
     * 得到app节点的签名字符串
     * @param map
     * @return
     */
    private String getInfoSign(Map<String,String> map,boolean isPost){
        return SignatureUtil.getSign(Url.BASE_URL, Url.INFO,map,isPost);
    }

    /**
     * 获取频道列表
     */
    public void getChannels(Subscriber<List<ChannelItem>> subscriber) {

        //发起请求时的Unix时间戳，使用格林威治标准时间
        int time = (int) TimeUtil.getGMTUnixTimeByCalendar();
        //参数键值对
        Map<String,String> map = new LinkedHashMap<>();
        map.put("method","get_channels");
        map.put("timestamp",time+"");

        Observable<String> observable = service.get_channels("get_channels",time,getInfoSign(map,false));
        Subscription subscription = observable //note2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, List<ChannelItem>>() {
                    @Override
                    public List<ChannelItem> call(String s) {
                        Result<GsonEntity> result = GsonUtil.GsonToBean(s,new TypeToken<Result<GsonEntity>>(){}.getType());
                        List<ChannelItem> channelItems = null;
                        if(result.getResponse_code() == 0){
                            channelItems =  result.getResponse_data().getChannels();
                        }
                        return channelItems;
                    }
                })
                .subscribe(subscriber);

    }


}
