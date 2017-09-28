package com.cdlixin.coc.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cdlixin.coc.model.greendaogen.DaoMaster;
import com.cdlixin.coc.utils.LogUtil;

import org.greenrobot.greendao.database.Database;

/**
 * 我的数据库帮助类
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper{
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        DaoMaster.createAllTables(db,true);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        if (oldVersion == newVersion) {
            LogUtil.e("数据库是最新版本" + oldVersion + "，不需要升级");
            return;
        }
        //数据库升级的时候，如果数据库没有改动,什么都不做，保留原来所有表格。
        //如果有新新增表格
//        DaoMaster.createAllTables(db, true);
////        SharedPreferencesHelper.getInstance().saveData("ContactGroupIsFirstSave",true);
//        String sql_19_01 = "alter table CHAT_MESSAGE add SEND_STATE text";
//        String sql_19_02 = "alter table GROUP_CHAT_ITEM add SEND_STATE text";
//        switch (oldVersion){
//            case 18:
//                db.execSQL(sql_19_01);
//                db.execSQL(sql_19_02);
//                break;
//        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
