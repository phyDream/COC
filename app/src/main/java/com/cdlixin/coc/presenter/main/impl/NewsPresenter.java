package com.cdlixin.coc.presenter.main.impl;

import android.content.Context;

import com.cdlixin.coc.entity.ChannelItem;
import com.cdlixin.coc.entity.GsonEntity;
import com.cdlixin.coc.entity.Result;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.global.BaseFrament;
import com.cdlixin.coc.global.BasePresenter;
import com.cdlixin.coc.global.MyApplication;
import com.cdlixin.coc.model.impl.ChannelModel;
import com.cdlixin.coc.presenter.main.INewsPresenter;
import com.cdlixin.coc.ui.main.view.NewsPageView;
import com.cdlixin.coc.utils.GsonUtil;
import com.cdlixin.coc.utils.LogUtil;
import com.cdlixin.coc.utils.ToastUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class NewsPresenter extends BasePresenter<NewsPageView> implements INewsPresenter{

    private NewsPageView view;
    private ChannelModel channelModel;

    public NewsPresenter(BaseActivity context, BaseFrament frament) {
        super(context, frament);
        this.view = (NewsPageView) frament;
        channelModel = ChannelModel.getInstance();
        DEBUG = false;
    }

    //本地获取可展示的频道列表
    Subscriber<List<ChannelItem>> subscriber = new Subscriber<List<ChannelItem>>() {
        @Override
        public void onCompleted() {
            showLog("频道列表本地获取完成");
        }

        @Override
        public void onError(Throwable e) {
            showLog("频道列表本地获取失败"+e.getMessage());
        }

        @Override
        public void onNext(List<ChannelItem> channels) {
            if(channels != null && channels.size() > 0){
                showLog("频道列表本地获取成功~"+channels.size()+"~"+channels);
                view.showChannel(channels);
            }else {
                view.showToast("频道获取失败");
            }
        }
    };

    @Override
    public void initChannels() {
        //网络获取并存储
        channelModel.saveChannels(new Subscriber<List<ChannelItem>>() {
            @Override
            public void onCompleted() {
                showLog("频道列表下载完成");
            }

            @Override
            public void onError(Throwable e) {
                showLog("频道列表下载失败" + e.getMessage());
            }

            @Override
            public void onNext(List<ChannelItem> channels) {
                showLog("频道列表下载成功~" + channels);
                //下载完成后从本地获取
                channelModel.getChannels(subscriber);
            }
        });



    }


}
