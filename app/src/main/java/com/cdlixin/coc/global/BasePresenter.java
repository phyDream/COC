package com.cdlixin.coc.global;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by 蒲弘宇 on 2017/9/26.
 *
 * 控制器基类
 */

public class BasePresenter<V>{

    public BaseActivity mContext;

    public BasePresenter(BaseActivity context) {
        mContext = context;
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

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
