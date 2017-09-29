package com.cdlixin.coc.model.impl;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.entity.GsonEntity;
import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.global.constants.Url;
import com.cdlixin.coc.model.dao.MyChannelItemDao;
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
    //数据库操作对象
    private MyChannelItemDao dao = MyChannelItemDao.getInstence();

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
     * 本地获取要展示的频道列表
     */
    public void getChannels (Subscriber<List<ChannelItem>> subscriber) {

        Observable<List<ChannelItem>> observable = Observable.create(new Observable.OnSubscribe<List<ChannelItem>>() {
            @Override
            public void call(Subscriber<? super List<ChannelItem>> subscriber) {
                List<ChannelItem> channels = dao.getUserChannel();
                if(channels != null && channels.size() > 0){
                    subscriber.onNext(channels);
                }
            }

        });
        Subscription subscription = observable //note2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 网络获取获取频道列表并保存
     * 设置频道顺序
     * 设置显示频道标记
     */
    public void saveChannels(Subscriber<List<ChannelItem>> subscriber) {

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
                .map(new Func1<String, List<ChannelItem>>() {//网络获取频道
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
                .distinct()//去重复
                .map(new Func1<List<ChannelItem>, List<ChannelItem>>() {//设置是否展示标记
                    @Override
                    public List<ChannelItem> call(List<ChannelItem> channelItems) {
                        for (int i = 0; i < channelItems.size(); i++) {
                            if(i < 7){
                                channelItems.get(i).setChose(true);
                            }else {
                                channelItems.get(i).setChose(false);
                            }
                        }
                        return channelItems;
                    }
                })
                .map(new Func1<List<ChannelItem>, List<ChannelItem>>() {//存入本地数据库
                    @Override
                    public List<ChannelItem> call(List<ChannelItem> channelItems) {
                        if(channelItems != null && channelItems.size() > 0){
                            for (int i = 0; i < channelItems.size(); i++) {
                                ChannelItem newItem = channelItems.get(i);
                                ChannelItem oldItem = dao.selectById(newItem.getId());
                                if (oldItem == null) {//当前数据库中没有就插入
                                    dao.insert(newItem);
                                }
                            }
                        }
                        return channelItems;
                    }
                })
                .subscribe(subscriber);

    }


}
