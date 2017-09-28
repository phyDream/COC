package com.cdlixin.coc.model.greendaogen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.cdlixin.coc.entity.ChannelItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHANNEL_ITEM".
*/
public class ChannelItemDao extends AbstractDao<ChannelItem, Long> {

    public static final String TABLENAME = "CHANNEL_ITEM";

    /**
     * Properties of entity ChannelItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property TableId = new Property(0, Long.class, "tableId", true, "_id");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property OrderId = new Property(3, Integer.class, "orderId", false, "ORDER_ID");
        public final static Property Selected = new Property(4, Integer.class, "selected", false, "SELECTED");
        public final static Property Chose = new Property(5, boolean.class, "chose", false, "CHOSE");
    }


    public ChannelItemDao(DaoConfig config) {
        super(config);
    }
    
    public ChannelItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHANNEL_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: tableId
                "\"ID\" TEXT," + // 1: id
                "\"NAME\" TEXT," + // 2: name
                "\"ORDER_ID\" INTEGER," + // 3: orderId
                "\"SELECTED\" INTEGER," + // 4: selected
                "\"CHOSE\" INTEGER NOT NULL );"); // 5: chose
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHANNEL_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChannelItem entity) {
        stmt.clearBindings();
 
        Long tableId = entity.getTableId();
        if (tableId != null) {
            stmt.bindLong(1, tableId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        Integer orderId = entity.getOrderId();
        if (orderId != null) {
            stmt.bindLong(4, orderId);
        }
 
        Integer selected = entity.getSelected();
        if (selected != null) {
            stmt.bindLong(5, selected);
        }
        stmt.bindLong(6, entity.getChose() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChannelItem entity) {
        stmt.clearBindings();
 
        Long tableId = entity.getTableId();
        if (tableId != null) {
            stmt.bindLong(1, tableId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        Integer orderId = entity.getOrderId();
        if (orderId != null) {
            stmt.bindLong(4, orderId);
        }
 
        Integer selected = entity.getSelected();
        if (selected != null) {
            stmt.bindLong(5, selected);
        }
        stmt.bindLong(6, entity.getChose() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ChannelItem readEntity(Cursor cursor, int offset) {
        ChannelItem entity = new ChannelItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // tableId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // orderId
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // selected
            cursor.getShort(offset + 5) != 0 // chose
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChannelItem entity, int offset) {
        entity.setTableId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOrderId(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setSelected(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setChose(cursor.getShort(offset + 5) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ChannelItem entity, long rowId) {
        entity.setTableId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ChannelItem entity) {
        if(entity != null) {
            return entity.getTableId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ChannelItem entity) {
        return entity.getTableId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}