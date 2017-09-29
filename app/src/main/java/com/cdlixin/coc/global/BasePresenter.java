package com.cdlixin.coc.global;

import com.cdlixin.coc.utils.LogUtil;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by 蒲弘宇 on 2017/9/26.
 *
 * 控制器基类
 */

public class BasePresenter<V>{

    public BaseActivity mContext;
    public BaseFrament frament;
    //是否处于调试模式
    public boolean DEBUG = true;

    public BasePresenter(BaseActivity context) {
        mContext = context;
    }

    public BasePresenter(BaseActivity context,BaseFrament frament) {
        mContext = context;
        this.frament = frament;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    //展示log
    protected void showLog(String string){
        if(DEBUG){
            LogUtil.i(string);
        }
    };

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
