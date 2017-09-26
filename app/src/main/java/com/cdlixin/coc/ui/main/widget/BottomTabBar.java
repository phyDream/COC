package com.cdlixin.coc.ui.main.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cdlixin.coc.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 蒲弘宇 on 2017/9/26.
 * <p>
 * app主界面的底部导航栏
 */

public class BottomTabBar extends LinearLayout {

    @Bind(R.id.rb_tab1)
    RadioButton rbTab1;
    @Bind(R.id.rb_tab2)
    RadioButton rbTab2;
    @Bind(R.id.rb_tab3)
    RadioButton rbTab3;
    @Bind(R.id.rb_tab4)
    RadioButton rbTab4;
    @Bind(R.id.rgTabs)
    RadioGroup rgTabs;
    @Bind(R.id.red_dot1)
    TextView redDot1;
    @Bind(R.id.red_dot2)
    TextView redDot2;
    @Bind(R.id.red_dot3)
    TextView redDot3;
    @Bind(R.id.red_dot4)
    TextView redDot4;


    private TabsClickListener tabsClickListener;

    public BottomTabBar(Context context) {
        super(context);
    }

    public BottomTabBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bottom_tab_bar, this, true);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    //初始化
    private void init() {

    }

    //业务逻辑实现
    private void doBusiness(){

    }

    //设置监听
    public void setTabsClickListener(TabsClickListener tabsClickListener){
        this.tabsClickListener = tabsClickListener;
    }

    @OnClick({R.id.rb_tab1, R.id.rb_tab2, R.id.rb_tab3, R.id.rb_tab4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_tab1:
                tabsClickListener.clickTabOne();
                break;
            case R.id.rb_tab2:
                tabsClickListener.clickTabTwo();
                break;
            case R.id.rb_tab3:
                tabsClickListener.clickTabThree();
                break;
            case R.id.rb_tab4:
                tabsClickListener.clickTabFour();
                break;
        }
    }

    //底部栏监听
    public interface TabsClickListener{
        void clickTabOne();
        void clickTabTwo();
        void clickTabThree();
        void clickTabFour();
    }
}
