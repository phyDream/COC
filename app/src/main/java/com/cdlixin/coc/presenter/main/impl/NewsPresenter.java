package com.cdlixin.coc.presenter.main.impl;

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
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class NewsPresenter extends BasePresenter implements INewsPresenter{

    private NewsPageView view;
    private ChannelModel channelModel;

    public NewsPresenter(BaseActivity context, BaseFrament frament) {
        super(context, frament);
        this.view = (NewsPageView) frament;
        channelModel = ChannelModel.getInstance();
    }

    @Override
    public void initChannels() {
        List<ChannelItem> channels = new ArrayList<>();
        Subscriber<List<ChannelItem>> subscriber = new Subscriber<List<ChannelItem>>() {
            @Override
            public void onCompleted() {
                LogUtil.i("频道列表下载完成");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("频道列表下载失败"+e.getMessage());
            }

            @Override
            public void onNext(List<ChannelItem> channels) {
                if(channels != null && channels.size() > 0){
                    LogUtil.i("频道列表下载成功~"+channels);
                    view.showChannel(channels);
                }else {

                }
            }
        };
        channelModel.getChannels(subscriber);
    }


}
