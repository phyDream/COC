package com.cdlixin.coc.global;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cdlixin.coc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、记录activity状态
 * 2、模板化activity中基础方法
 * 3、
 * 4、
 * 5、
 */
public abstract class BaseActivity extends FragmentActivity {

    /**
     * 记录处于前台的Activity
     */
    private static BaseActivity mForegroundActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.mActivities.add(this);//存放所有activity的引用
        setContentView(getLayoutId());
        initView();
        initFindViewById();
        initData();
        initEvent();
    }


    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mForegroundActivity = null;
        super.onPause();
    }

    abstract protected int getLayoutId();

    abstract protected void initView();

    abstract protected void initData();

    //注册事件
    protected void register() {

    }

    /**
     * 关闭所有Activity
     */
    public void finishAll() {
        List<BaseActivity> copy;
        synchronized (MyApplication.mActivities) {
            copy = new ArrayList<BaseActivity>(MyApplication.mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
    }

    /**
     * 关闭所有Activity，除了参数传递的Activity
     */
    public void finishAll(BaseActivity except) {
        List<BaseActivity> copy;
        synchronized (MyApplication.mActivities) {
            copy = new ArrayList<BaseActivity>(MyApplication.mActivities);
        }
        for (BaseActivity activity : copy) {
            if (activity != except)
                activity.finish();
        }
    }

    /**
     * 是否有启动的Activity
     */
    public boolean hasActivity() {
        return MyApplication.mActivities.size() > 0;
    }

    /**
     * 获取当前处于前台的activity
     */
    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 获取当前处于栈顶的activity，无论其是否处于前台
     */
    public BaseActivity getCurrentActivity() {
        List<BaseActivity> copy;
        synchronized (MyApplication.mActivities) {
            copy = new ArrayList<BaseActivity>(MyApplication.mActivities);
        }
        if (copy.size() > 0) {
            return copy.get(copy.size() - 1);
        }
        return null;
    }

    protected void initFindViewById() {

    }

    protected void initEvent() {

    }

    /**
     * 退出应用
     */
    public void exitApp() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
