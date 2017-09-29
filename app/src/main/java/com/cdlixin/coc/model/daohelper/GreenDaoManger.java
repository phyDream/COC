package com.cdlixin.coc.model.daohelper;


import com.cdlixin.coc.model.greendaogen.DaoSession;

/**
 * 提供会话
 */

public class GreenDaoManger {

    private static GreenDaoManger mInstance;//单例
    private GreenDaoManger(){}
    public static GreenDaoManger getInstance(){
        if(mInstance == null){
            synchronized (GreenDaoManger.class){
                if (mInstance == null){
                    mInstance = new GreenDaoManger();
                }
            }
        }
        return mInstance;
    }

    //获取数据库会话对象
    public DaoSession getDaosession(){
        return DataBaseManager.getInstance().getDaoMaster().newSession();
    }
}
