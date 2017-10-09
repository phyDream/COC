package com.cdlixin.coc.ui.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 蒲弘宇 on 2017/10/9.
 * 自定义TitleBar
 */

public class TitleBar extends RelativeLayout {

    private final String Title = MyApplication.getAppContext().getString(R.string.Title);
    private final int defultColor = getResources().getColor(R.color.colorTitle);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private View view;
    private String title = Title;
    private int backColor;
    private boolean isWithBack = true;

    private ClickBackListener clickBackListener;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.title_bar, this, true);
        ButterKnife.bind(this);
        if (null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyle, 0);
            title = ta.getString(R.styleable.TitleBar_title);
            backColor = ta.getColor(R.styleable.TitleBar_titleBar_color, defultColor);
            isWithBack = ta.getBoolean(R.styleable.TitleBar_isWithBack,true);
            //回收
            ta.recycle();
        }
        init();
        doBusiness();
        setListener();
    }

    private void init() {
        this.setBackgroundColor(backColor);
        tvTitle.setText(title);
        if(!isWithBack){
            imgBack.setVisibility(GONE);
        }
    }

    public void setClickBackListener(ClickBackListener clickBackListener) {
        this.clickBackListener = clickBackListener;
    }

    private void doBusiness() {
    }

    private void setListener() {
        imgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickBackListener != null){
                    clickBackListener.clickBack();
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    interface ClickBackListener{
        void clickBack();
    }


}
