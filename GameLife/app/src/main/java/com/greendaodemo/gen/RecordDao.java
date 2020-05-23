package com.greendaodemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import ftd.txf.com.gamelife.entity.Record;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RECORD".
*/
public class RecordDao extends AbstractDao<Record, Long> {

    public static final String TABLENAME = "RECORD";

    /**
     * Properties of entity Record.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property R_id = new Property(0, Long.class, "r_id", true, "_id");
        public final static Property P_id = new Property(1, int.class, "p_id", false, "P_ID");
        public final static Property Lastday = new Property(2, int.class, "lastday", false, "LASTDAY");
        public final static Property MaxOneDay = new Property(3, int.class, "MaxOneDay", false, "MAX_ONE_DAY");
        public final static Property MaxWorktime = new Property(4, int.class, "MaxWorktime", false, "MAX_WORKTIME");
        public final static Property Dayonnoew = new Property(5, int.class, "dayonnoew", false, "DAYONNOEW");
        public final static Property Study_times = new Property(6, int.class, "study_times", false, "STUDY_TIMES");
        public final static Property Excise_times = new Property(7, int.class, "excise_times", false, "EXCISE_TIMES");
        public final static Property Extend_times = new Property(8, int.class, "extend_times", false, "EXTEND_TIMES");
        public final static Property Alltime = new Property(9, int.class, "alltime", false, "ALLTIME");
        public final static Property Morebiaozhi = new Property(10, int.class, "morebiaozhi", false, "MOREBIAOZHI");
    }


    public RecordDao(DaoConfig config) {
        super(config);
    }
    
    public RecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: r_id
                "\"P_ID\" INTEGER NOT NULL ," + // 1: p_id
                "\"LASTDAY\" INTEGER NOT NULL ," + // 2: lastday
                "\"MAX_ONE_DAY\" INTEGER NOT NULL ," + // 3: MaxOneDay
                "\"MAX_WORKTIME\" INTEGER NOT NULL ," + // 4: MaxWorktime
                "\"DAYONNOEW\" INTEGER NOT NULL ," + // 5: dayonnoew
                "\"STUDY_TIMES\" INTEGER NOT NULL ," + // 6: study_times
                "\"EXCISE_TIMES\" INTEGER NOT NULL ," + // 7: excise_times
                "\"EXTEND_TIMES\" INTEGER NOT NULL ," + // 8: extend_times
                "\"ALLTIME\" INTEGER NOT NULL ," + // 9: alltime
                "\"MOREBIAOZHI\" INTEGER NOT NULL );"); // 10: morebiaozhi
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Record entity) {
        stmt.clearBindings();
 
        Long r_id = entity.getR_id();
        if (r_id != null) {
            stmt.bindLong(1, r_id);
        }
        stmt.bindLong(2, entity.getP_id());
        stmt.bindLong(3, entity.getLastday());
        stmt.bindLong(4, entity.getMaxOneDay());
        stmt.bindLong(5, entity.getMaxWorktime());
        stmt.bindLong(6, entity.getDayonnoew());
        stmt.bindLong(7, entity.getStudy_times());
        stmt.bindLong(8, entity.getExcise_times());
        stmt.bindLong(9, entity.getExtend_times());
        stmt.bindLong(10, entity.getAlltime());
        stmt.bindLong(11, entity.getMorebiaozhi());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Record entity) {
        stmt.clearBindings();
 
        Long r_id = entity.getR_id();
        if (r_id != null) {
            stmt.bindLong(1, r_id);
        }
        stmt.bindLong(2, entity.getP_id());
        stmt.bindLong(3, entity.getLastday());
        stmt.bindLong(4, entity.getMaxOneDay());
        stmt.bindLong(5, entity.getMaxWorktime());
        stmt.bindLong(6, entity.getDayonnoew());
        stmt.bindLong(7, entity.getStudy_times());
        stmt.bindLong(8, entity.getExcise_times());
        stmt.bindLong(9, entity.getExtend_times());
        stmt.bindLong(10, entity.getAlltime());
        stmt.bindLong(11, entity.getMorebiaozhi());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Record readEntity(Cursor cursor, int offset) {
        Record entity = new Record( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // r_id
            cursor.getInt(offset + 1), // p_id
            cursor.getInt(offset + 2), // lastday
            cursor.getInt(offset + 3), // MaxOneDay
            cursor.getInt(offset + 4), // MaxWorktime
            cursor.getInt(offset + 5), // dayonnoew
            cursor.getInt(offset + 6), // study_times
            cursor.getInt(offset + 7), // excise_times
            cursor.getInt(offset + 8), // extend_times
            cursor.getInt(offset + 9), // alltime
            cursor.getInt(offset + 10) // morebiaozhi
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Record entity, int offset) {
        entity.setR_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setP_id(cursor.getInt(offset + 1));
        entity.setLastday(cursor.getInt(offset + 2));
        entity.setMaxOneDay(cursor.getInt(offset + 3));
        entity.setMaxWorktime(cursor.getInt(offset + 4));
        entity.setDayonnoew(cursor.getInt(offset + 5));
        entity.setStudy_times(cursor.getInt(offset + 6));
        entity.setExcise_times(cursor.getInt(offset + 7));
        entity.setExtend_times(cursor.getInt(offset + 8));
        entity.setAlltime(cursor.getInt(offset + 9));
        entity.setMorebiaozhi(cursor.getInt(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Record entity, long rowId) {
        entity.setR_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Record entity) {
        if(entity != null) {
            return entity.getR_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Record entity) {
        return entity.getR_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
