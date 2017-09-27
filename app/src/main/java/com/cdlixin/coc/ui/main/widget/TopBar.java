package com.cdlixin.coc.ui.main.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cdlixin.coc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 蒲弘宇 on 2017/9/27.
 */

public class TopBar extends LinearLayout {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.btn_add_channel)
    Button btnAddChannel;

    public TopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.channel_tabs, this, true);
        ButterKnife.bind(this);
        init();
        doBusiness();
        setListener();
    }

    private void init() {
    }

    private void doBusiness() {
    }

    private void setListener() {
    }

    public TabLayout getTabLayout(){
        return tabLayout;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }
}
