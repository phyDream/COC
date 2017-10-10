package com.cdlixin.coc.utils;

/**
 * 按钮点击事件过滤 - 防止连续点击打开两个相同的页面（安卓5.0以下可能发生）
 */
public class ClickFilter {
    public static final long INTERVAL = 500L; //防止连续点击的时间间隔
    private static long lastClickTime = 0L; //上一次点击的时间

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < INTERVAL) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
