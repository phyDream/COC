package com.cdlixin.coc.presenter.news.impl;

import android.content.Context;

import com.cdlixin.coc.entity.NewsEntity;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.global.MyApplication;
import com.cdlixin.coc.model.impl.NewsModel;
import com.cdlixin.coc.ui.news.view.NewsListView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class NewsListPresenter extends BasePresenter<NewsListView>{

    private Context context;
    private NewsListView view ;
    private NewsModel model;
    private String s_token;

    public NewsListPresenter(BaseActivity context) {
        super(context);
    }

    public NewsListPresenter(BaseActivity context, BaseFrament frament) {
        super(context, frament);
        this.context = context;
        view = (NewsListView) frament;
        model = NewsModel.getInstance();
        DEBUG = true;
    }

    /**
     * 获取新闻列表并设置
     */
    public void setNews(int channel_id,int page){
        //本地获取可展示的频道列表
        Subscriber<List<NewsEntity>> subscriber = new Subscriber<List<NewsEntity>>() {
            @Override
            public void onCompleted() {
                showLog("资讯列表本地获取完成");
            }

            @Override
            public void onError(Throwable e) {
                showLog("资讯列表本地获取失败"+e.getMessage());
            }

            @Override
            public void onNext(List<NewsEntity> newsEntities) {
                if(newsEntities != null && newsEntities.size() > 0){
                    showLog("资讯列表本地获取成功~"+newsEntities.size()+"~"+newsEntities);
                    view.showNews(newsEntities);
                }else {
                    view.showToast("资讯获取失败");
                }
            }
        };

        model.getNews(0,Integer.MAX_VALUE,s_token,channel_id,page, MyApplication.NEWS_COUNT,subscriber);
    }


}
