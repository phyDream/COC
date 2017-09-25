package com.cdlixin.coc.global;

import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 蒲弘宇 on 2017/9/25.
 */

public class MyApplication extends Application{

    /**
     * 记录所有活动的Activity
     */
    public static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

}
