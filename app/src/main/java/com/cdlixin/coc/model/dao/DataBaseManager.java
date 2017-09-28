package com.cdlixin.coc.model.dao;

import android.content.Context;

import com.cdlixin.coc.model.greendaogen.DaoMaster;
import com.cdlixin.coc.model.greendaogen.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;


/**
 *数据库管理
 */

public class DataBaseManager {
    private DaoMaster mDaoMaster= null ;
    private DaoSession mDaoSession = null;
    public static final String DB_NAME = "cdlixing.db";

    //自定义的DaoHelper
    private MySQLiteOpenHelper mHelper = null;
    private static DataBaseManager mInstance =null;//单例
    private Database db = null;

    private DataBaseManager(Context context){
        mHelper = new MySQLiteOpenHelper(context,DB_NAME, null);
        db = mHelper.getWritableDb();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }


    //获取单例对象。
    public static DataBaseManager getInstance() {
        if (mInstance == null) {
            throw new RuntimeException("class should init!");
        }
        return mInstance;
    }

    public static synchronized void init(Context context){

        if(mInstance == null){
            mInstance = new DataBaseManager(context);

        }
    }

    public DaoMaster getDaoMaster(){
        return mDaoMaster;
    }


    /**
     * 设置debug模式开启或关闭，默认关闭
     * @param flag
     */
    public void setDebug(boolean flag){
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    //删除所有表
    public void dropAllTables(){
        DaoMaster.dropAllTables(db,true);
    }
    //重新建表
    public void createAllTables(){
        DaoMaster.createAllTables(db,true);
    }

    /**
     * 关闭数据库
     */
    public void close(){
        closeHelper();
        closeDaoSession();
    }

    private void closeDaoSession(){
        if (null != mDaoSession){
            mDaoSession.clear();
            mDaoSession = null;
        }
    }
    private  void  closeHelper(){
        if (mHelper!=null){
            mHelper.close();
            mHelper = null;
        }
    }
}
