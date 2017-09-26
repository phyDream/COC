package com.cdlixin.coc.global;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cdlixin.coc.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 1、记录activity状态
 * 2、模板化activity中基础方法：界面初始化，数据初始化，监听初始化
 * 3、简化基本方法书写：跳转
 * 4、网络状态监听
 * 5、
 */
public abstract class BaseActivity <V, T extends BasePresenter<V>> extends FragmentActivity {

    /**
     * 记录处于前台的Activity
     */
    private static BaseActivity mForegroundActivity = null;

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.mActivities.add(this);//存放所有activity的引用
        setContentView(getLayoutId());//设置布局id
        ButterKnife.bind(this);//控件绑定
        presenter = getPresenter();
        initView();//初始化界面
        initFindViewById();//
        initData();//初始化数据
        initEvent();//初始化事件
        setListener();//设置监听事件
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    abstract protected int getLayoutId();

    abstract protected void initView();

    abstract protected void initData();

    public abstract T getPresenter();

    abstract protected void setListener();

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this,clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

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
