package com.cdlixin.coc.model.greendaogen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.cdlixin.coc.entity.NewsEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_ENTITY".
*/
public class NewsEntityDao extends AbstractDao<NewsEntity, Void> {

    public static final String TABLENAME = "NEWS_ENTITY";

    /**
     * Properties of entity NewsEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Info_id = new Property(0, String.class, "info_id", false, "INFO_ID");
        public final static Property Info_pic_url = new Property(1, String.class, "info_pic_url", false, "INFO_PIC_URL");
        public final static Property Info_title = new Property(2, String.class, "info_title", false, "INFO_TITLE");
        public final static Property Info_sum = new Property(3, String.class, "info_sum", false, "INFO_SUM");
        public final static Property Info_date = new Property(4, String.class, "info_date", false, "INFO_DATE");
        public final static Property Info_owner = new Property(5, String.class, "info_owner", false, "INFO_OWNER");
        public final static Property Info_source = new Property(6, String.class, "info_source", false, "INFO_SOURCE");
        public final static Property Info_text = new Property(7, String.class, "info_text", false, "INFO_TEXT");
        public final static Property ChannelId = new Property(8, String.class, "channelId", false, "CHANNEL_ID");
        public final static Property WhatEver = new Property(9, String.class, "whatEver", false, "WHAT_EVER");
    }


    public NewsEntityDao(DaoConfig config) {
        super(config);
    }
    
    public NewsEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_ENTITY\" (" + //
                "\"INFO_ID\" TEXT," + // 0: info_id
                "\"INFO_PIC_URL\" TEXT," + // 1: info_pic_url
                "\"INFO_TITLE\" TEXT," + // 2: info_title
                "\"INFO_SUM\" TEXT," + // 3: info_sum
                "\"INFO_DATE\" TEXT," + // 4: info_date
                "\"INFO_OWNER\" TEXT," + // 5: info_owner
                "\"INFO_SOURCE\" TEXT," + // 6: info_source
                "\"INFO_TEXT\" TEXT," + // 7: info_text
                "\"CHANNEL_ID\" TEXT," + // 8: channelId
                "\"WHAT_EVER\" TEXT);"); // 9: whatEver
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsEntity entity) {
        stmt.clearBindings();
 
        String info_id = entity.getInfo_id();
        if (info_id != null) {
            stmt.bindString(1, info_id);
        }
 
        String info_pic_url = entity.getInfo_pic_url();
        if (info_pic_url != null) {
            stmt.bindString(2, info_pic_url);
        }
 
        String info_title = entity.getInfo_title();
        if (info_title != null) {
            stmt.bindString(3, info_title);
        }
 
        String info_sum = entity.getInfo_sum();
        if (info_sum != null) {
            stmt.bindString(4, info_sum);
        }
 
        String info_date = entity.getInfo_date();
        if (info_date != null) {
            stmt.bindString(5, info_date);
        }
 
        String info_owner = entity.getInfo_owner();
        if (info_owner != null) {
            stmt.bindString(6, info_owner);
        }
 
        String info_source = entity.getInfo_source();
        if (info_source != null) {
            stmt.bindString(7, info_source);
        }
 
        String info_text = entity.getInfo_text();
        if (info_text != null) {
            stmt.bindString(8, info_text);
        }
 
        String channelId = entity.getChannelId();
        if (channelId != null) {
            stmt.bindString(9, channelId);
        }
 
        String whatEver = entity.getWhatEver();
        if (whatEver != null) {
            stmt.bindString(10, whatEver);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsEntity entity) {
        stmt.clearBindings();
 
        String info_id = entity.getInfo_id();
        if (info_id != null) {
            stmt.bindString(1, info_id);
        }
 
        String info_pic_url = entity.getInfo_pic_url();
        if (info_pic_url != null) {
            stmt.bindString(2, info_pic_url);
        }
 
        String info_title = entity.getInfo_title();
        if (info_title != null) {
            stmt.bindString(3, info_title);
        }
 
        String info_sum = entity.getInfo_sum();
        if (info_sum != null) {
            stmt.bindString(4, info_sum);
        }
 
        String info_date = entity.getInfo_date();
        if (info_date != null) {
            stmt.bindString(5, info_date);
        }
 
        String info_owner = entity.getInfo_owner();
        if (info_owner != null) {
            stmt.bindString(6, info_owner);
        }
 
        String info_source = entity.getInfo_source();
        if (info_source != null) {
            stmt.bindString(7, info_source);
        }
 
        String info_text = entity.getInfo_text();
        if (info_text != null) {
            stmt.bindString(8, info_text);
        }
 
        String channelId = entity.getChannelId();
        if (channelId != null) {
            stmt.bindString(9, channelId);
        }
 
        String whatEver = entity.getWhatEver();
        if (whatEver != null) {
            stmt.bindString(10, whatEver);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public NewsEntity readEntity(Cursor cursor, int offset) {
        NewsEntity entity = new NewsEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // info_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // info_pic_url
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // info_title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // info_sum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // info_date
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // info_owner
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // info_source
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // info_text
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // channelId
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // whatEver
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsEntity entity, int offset) {
        entity.setInfo_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setInfo_pic_url(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setInfo_title(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setInfo_sum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setInfo_date(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setInfo_owner(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setInfo_source(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setInfo_text(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setChannelId(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setWhatEver(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(NewsEntity entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(NewsEntity entity) {
        return null;
    }

    @Override
    public boolean hasKey(NewsEntity entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}