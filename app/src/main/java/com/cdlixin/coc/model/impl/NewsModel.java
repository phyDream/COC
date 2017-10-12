package com.cdlixin.coc.model.impl;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.entity.GsonEntity;
import com.cdlixin.coc.entity.NewsEntity;
import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.global.constants.Url;
import com.cdlixin.coc.model.dao.MyNewsDao;
import com.cdlixin.coc.model.network.HttpService;
import com.cdlixin.coc.model.network.RequestManger;
import com.cdlixin.coc.utils.GsonUtil;
import com.cdlixin.coc.utils.LogUtil;
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
 * Created by 蒲弘宇 on 2017/9/30.
 */

public class NewsModel {
    //请求生成器
    private HttpService service = new RequestManger(Url.BASE_URL).getService();
    private MyNewsDao dao = MyNewsDao.getInstance();
    private static NewsModel newsModel = null;
    //是否处于调试模式
    public boolean DEBUG = true;

    public static NewsModel getInstance(){
        if(newsModel == null){
            synchronized (NewsModel.class){
                if(newsModel == null){
                    newsModel = new NewsModel();
                }
            }
        }
        return newsModel;
    }

    /**
     * 得到app节点的签名字符串
     * @param map
     * @return
     */
    private String getInfoSign(Map<String,String> map, boolean isPost){
        return SignatureUtil.getSign(Url.BASE_URL, Url.INFO,map,isPost);
    }

    /**
     * 网络获取新闻列表数据并保存
     * @param subscriber
     */
    public void getNews(int from_date,int to_date,String s_token, int channel_id, int page, int size,Subscriber<List<NewsEntity>> subscriber){
        //发起请求时的Unix时间戳，使用格林威治标准时间
        int time = (int) TimeUtil.getGMTUnixTimeByCalendar();
        //参数键值对
        Map<String,String> map = new LinkedHashMap<>();
        map.put("method","get_infos");
        map.put("timestamp",time+"");
        map.put("from_date",from_date+"");
        map.put("to_date",to_date+"");
        map.put("s_token",s_token);
        map.put("channel_id",channel_id+"");
        map.put("page",page+"");
        map.put("size",size+"");

        Observable<String> call = service.get_infos("get_infos",time,from_date,to_date,s_token,channel_id,page,size,getInfoSign(map,false));
        Subscription subscription = call //note2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, List<NewsEntity>>() {
                    @Override
                    public List<NewsEntity> call(String s) {
                        showLog("~资讯string~"+s);
                        List<NewsEntity> news = null;
                        Result<GsonEntity> result = GsonUtil.GsonToBean(s,new TypeToken<Result<GsonEntity>>(){}.getType());
                        if(result.getResponse_code() == 0){
                            news =  result.getResponse_data().getNews();
                            showLog("~资讯~"+news);
                        }
                        return news;
                    }
                })
                .map(new Func1<List<NewsEntity>, List<NewsEntity>>() {
                    @Override
                    public List<NewsEntity> call(List<NewsEntity> newsEntities) {
                        if(newsEntities != null && newsEntities.size() > 0){
                            for (int i = 0; i < newsEntities.size(); i++) {
                                NewsEntity newItem = newsEntities.get(i);
                                NewsEntity oldItem = dao.selectById(newItem.getInfo_id() );
                                if (oldItem == null) {//当前数据库中没有就插入
                                    dao.insert(newItem);
                                }
                            }
                        }
                        showLog("~新闻列表~"+newsEntities.size()+"~"+newsEntities);
                        return newsEntities;
                    }
                })
                .subscribe(subscriber);
    }

    //展示log
    protected void showLog(String string){
        if(DEBUG){
            LogUtil.i(string);
        }
    };
}
