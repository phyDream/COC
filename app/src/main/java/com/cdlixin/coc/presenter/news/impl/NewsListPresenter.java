package com.cdlixin.coc.presenter.news.impl;

import android.content.Context;

import com.cdlixin.coc.R;
import com.cdlixin.coc.entity.NewsEntity;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.global.MyApplication;
import com.cdlixin.coc.model.impl.NewsModel;
import com.cdlixin.coc.model.impl.UserModel;
import com.cdlixin.coc.ui.news.view.NewsListView;
import com.cdlixin.coc.utils.NetWorkUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class NewsListPresenter extends BasePresenter<NewsListView>{

    private Context context;
    private NewsListView view ;
    private NewsModel model;
    private UserModel userModel;
    private String s_token;

    public NewsListPresenter(BaseActivity context) {
        super(context);
    }

    public NewsListPresenter(BaseActivity context, BaseFrament frament) {
        super(context, frament);
        this.context = context;
        view = (NewsListView) frament;
        model = NewsModel.getInstance();
        userModel = UserModel.getInstance();
        DEBUG = true;
    }

    /**
     * 获取新闻列表并设置
     */
    public void getNews(int channel_id,int page){
        s_token = userModel.getToken();
        //本地获取可展示的频道列表
        Subscriber<List<NewsEntity>> subscriber = new Subscriber<List<NewsEntity>>() {
            @Override
            public void onCompleted() {
                showLog("资讯列表本地获取完成~");
            }

            @Override
            public void onError(Throwable e) {
                showLog("资讯列表本地获取失败~"+e.getMessage());
                view.showNews(null);
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
        showLog("~发起资讯请求~"+s_token);
        model.getNews(0,Integer.MAX_VALUE,s_token,channel_id,page, MyApplication.NEWS_COUNT,subscriber);
        if(!NetWorkUtil.isNetworkConnected()){
            view.showToast(MyApplication.geResStr(R.string.Network_connection_failed));
        }

    }


}
