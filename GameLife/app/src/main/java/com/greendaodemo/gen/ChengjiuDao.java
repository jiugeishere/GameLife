package com.greendaodemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import ftd.txf.com.gamelife.entity.Chengjiu;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHENGJIU".
*/
public class ChengjiuDao extends AbstractDao<Chengjiu, Long> {

    public static final String TABLENAME = "CHENGJIU";

    /**
     * Properties of entity Chengjiu.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property C_id = new Property(0, Long.class, "c_id", true, "_id");
        public final static Property Chengjiu_id = new Property(1, Long.class, "chengjiu_id", false, "CHENGJIU_ID");
        public final static Property Chengjiu_name = new Property(2, String.class, "chengjiu_name", false, "CHENGJIU_NAME");
        public final static Property Chengjiu_limit = new Property(3, int.class, "chengjiu_limit", false, "CHENGJIU_LIMIT");
        public final static Property Chengjiu_photo = new Property(4, int.class, "chengjiu_photo", false, "CHENGJIU_PHOTO");
        public final static Property Chengjiu_style = new Property(5, String.class, "chengjiu_style", false, "CHENGJIU_STYLE");
        public final static Property Chengjiu_time = new Property(6, String.class, "chengjiu_time", false, "CHENGJIU_TIME");
        public final static Property Chengjiu_done = new Property(7, boolean.class, "chengjiu_done", false, "CHENGJIU_DONE");
        public final static Property Chengjiu_jingdu = new Property(8, int.class, "chengjiu_jingdu", false, "CHENGJIU_JINGDU");
    }


    public ChengjiuDao(DaoConfig config) {
        super(config);
    }
    
    public ChengjiuDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHENGJIU\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: c_id
                "\"CHENGJIU_ID\" INTEGER," + // 1: chengjiu_id
                "\"CHENGJIU_NAME\" TEXT," + // 2: chengjiu_name
                "\"CHENGJIU_LIMIT\" INTEGER NOT NULL ," + // 3: chengjiu_limit
                "\"CHENGJIU_PHOTO\" INTEGER NOT NULL ," + // 4: chengjiu_photo
                "\"CHENGJIU_STYLE\" TEXT," + // 5: chengjiu_style
                "\"CHENGJIU_TIME\" TEXT," + // 6: chengjiu_time
                "\"CHENGJIU_DONE\" INTEGER NOT NULL ," + // 7: chengjiu_done
                "\"CHENGJIU_JINGDU\" INTEGER NOT NULL );"); // 8: chengjiu_jingdu
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHENGJIU\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Chengjiu entity) {
        stmt.clearBindings();
 
        Long c_id = entity.getC_id();
        if (c_id != null) {
            stmt.bindLong(1, c_id);
        }
 
        Long chengjiu_id = entity.getChengjiu_id();
        if (chengjiu_id != null) {
            stmt.bindLong(2, chengjiu_id);
        }
 
        String chengjiu_name = entity.getChengjiu_name();
        if (chengjiu_name != null) {
            stmt.bindString(3, chengjiu_name);
        }
        stmt.bindLong(4, entity.getChengjiu_limit());
        stmt.bindLong(5, entity.getChengjiu_photo());
 
        String chengjiu_style = entity.getChengjiu_style();
        if (chengjiu_style != null) {
            stmt.bindString(6, chengjiu_style);
        }
 
        String chengjiu_time = entity.getChengjiu_time();
        if (chengjiu_time != null) {
            stmt.bindString(7, chengjiu_time);
        }
        stmt.bindLong(8, entity.getChengjiu_done() ? 1L: 0L);
        stmt.bindLong(9, entity.getChengjiu_jingdu());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Chengjiu entity) {
        stmt.clearBindings();
 
        Long c_id = entity.getC_id();
        if (c_id != null) {
            stmt.bindLong(1, c_id);
        }
 
        Long chengjiu_id = entity.getChengjiu_id();
        if (chengjiu_id != null) {
            stmt.bindLong(2, chengjiu_id);
        }
 
        String chengjiu_name = entity.getChengjiu_name();
        if (chengjiu_name != null) {
            stmt.bindString(3, chengjiu_name);
        }
        stmt.bindLong(4, entity.getChengjiu_limit());
        stmt.bindLong(5, entity.getChengjiu_photo());
 
        String chengjiu_style = entity.getChengjiu_style();
        if (chengjiu_style != null) {
            stmt.bindString(6, chengjiu_style);
        }
 
        String chengjiu_time = entity.getChengjiu_time();
        if (chengjiu_time != null) {
            stmt.bindString(7, chengjiu_time);
        }
        stmt.bindLong(8, entity.getChengjiu_done() ? 1L: 0L);
        stmt.bindLong(9, entity.getChengjiu_jingdu());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Chengjiu readEntity(Cursor cursor, int offset) {
        Chengjiu entity = new Chengjiu( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // c_id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // chengjiu_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // chengjiu_name
            cursor.getInt(offset + 3), // chengjiu_limit
            cursor.getInt(offset + 4), // chengjiu_photo
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // chengjiu_style
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // chengjiu_time
            cursor.getShort(offset + 7) != 0, // chengjiu_done
            cursor.getInt(offset + 8) // chengjiu_jingdu
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Chengjiu entity, int offset) {
        entity.setC_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChengjiu_id(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setChengjiu_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setChengjiu_limit(cursor.getInt(offset + 3));
        entity.setChengjiu_photo(cursor.getInt(offset + 4));
        entity.setChengjiu_style(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setChengjiu_time(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setChengjiu_done(cursor.getShort(offset + 7) != 0);
        entity.setChengjiu_jingdu(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Chengjiu entity, long rowId) {
        entity.setC_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Chengjiu entity) {
        if(entity != null) {
            return entity.getC_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Chengjiu entity) {
        return entity.getC_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}