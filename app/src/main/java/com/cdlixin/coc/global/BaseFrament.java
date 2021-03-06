package com.cdlixin.coc.global;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdlixin.coc.utils.LogUtil;
import com.cdlixin.coc.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by 蒲弘宇 on 2017/9/26.
 */

public abstract class BaseFrament <V, T extends BasePresenter<V>> extends Fragment implements View.OnClickListener{

    private View mContextView = null;
    protected T mPresenter;
    //是否处于调试模式
    public boolean DEBUG = true;
    public Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否使用MVP模式
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 是否第一次初始化数据
     */
    private boolean isFristInit = true;

    /**
     * 监测fragment是否显示，若是第一次变为显示就初始化数据
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //第一次初始化&&由隐藏变为显示
        if(isFristInit && !hidden){
            isFristInit = false;
            showLog("~fragment第一次由隐藏变为显示时初始化数据");
            initData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContextView = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(this, mContextView);
        initView(mContextView);
        activity = getActivity();
        setListener();
        doBusiness(getActivity());
        return mContextView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);

    /**
     * 第一次显示时初始化数据
     */
    protected abstract void initData();

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /** View点击 **/
    public abstract void setListener();

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T getPresenter();

    //展示log
    protected void showLog(String string){
        if(DEBUG){
            LogUtil.i(string);
        }
    };

    @SuppressWarnings("unchecked")
    public <T extends View> T $(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

}
