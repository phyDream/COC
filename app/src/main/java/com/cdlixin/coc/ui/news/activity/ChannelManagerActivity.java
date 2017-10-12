package com.cdlixin.coc.ui.news.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.presenter.news.impl.ChannelManagerPresenter;
import com.cdlixin.coc.ui.news.view.ChannelManagerView;
import com.cdlixin.coc.ui.news.widget.DragGrid;
import com.cdlixin.coc.ui.news.widget.OtherGridView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChannelManagerActivity extends BaseActivity<ChannelManagerView, ChannelManagerPresenter> {

    @Bind(R.id.tv_channel)
    TextView tvChannel;
    @Bind(R.id.tv_hint_activity_channel)
    TextView tvHintActivityChannel;
    @Bind(R.id.grid_MyChannel)
    DragGrid gridMyChannel;
    @Bind(R.id.grid_otherChannel)
    OtherGridView gridOtherChannel;
    @Bind(R.id.activity_channel)
    RelativeLayout activityChannel;
    @Bind(R.id.btn_back)
    ImageView btnBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_channel_manager;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public ChannelManagerPresenter getPresenter() {
        return null;
    }

    @Override
    protected void setListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
