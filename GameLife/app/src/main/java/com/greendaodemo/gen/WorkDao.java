package com.greendaodemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import ftd.txf.com.gamelife.entity.Work;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORK".
*/
public class WorkDao extends AbstractDao<Work, Long> {

    public static final String TABLENAME = "WORK";

    /**
     * Properties of entity Work.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property W_id = new Property(0, Long.class, "w_id", true, "_id");
        public final static Property Monster_img = new Property(1, int.class, "Monster_img", false, "MONSTER_IMG");
        public final static Property Monster_name = new Property(2, String.class, "Monster_name", false, "MONSTER_NAME");
        public final static Property Work_name = new Property(3, String.class, "work_name", false, "WORK_NAME");
        public final static Property Plan_time = new Property(4, int.class, "plan_time", false, "PLAN_TIME");
        public final static Property Monster_exp = new Property(5, int.class, "Monster_exp", false, "MONSTER_EXP");
        public final static Property Monster_gold = new Property(6, int.class, "Monster_gold", false, "MONSTER_GOLD");
        public final static Property Monster_shuxing = new Property(7, String.class, "Monster_shuxing", false, "MONSTER_SHUXING");
        public final static Property Work_ways = new Property(8, String.class, "work_ways", false, "WORK_WAYS");
        public final static Property Work_mubiao = new Property(9, String.class, "work_mubiao", false, "WORK_MUBIAO");
        public final static Property Ptow_id = new Property(10, Long.class, "ptow_id", false, "PTOW_ID");
        public final static Property Work_createtime = new Property(11, String.class, "work_createtime", false, "WORK_CREATETIME");
    }


    public WorkDao(DaoConfig config) {
        super(config);
    }
    
    public WorkDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORK\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: w_id
                "\"MONSTER_IMG\" INTEGER NOT NULL ," + // 1: Monster_img
                "\"MONSTER_NAME\" TEXT," + // 2: Monster_name
                "\"WORK_NAME\" TEXT," + // 3: work_name
                "\"PLAN_TIME\" INTEGER NOT NULL ," + // 4: plan_time
                "\"MONSTER_EXP\" INTEGER NOT NULL ," + // 5: Monster_exp
                "\"MONSTER_GOLD\" INTEGER NOT NULL ," + // 6: Monster_gold
                "\"MONSTER_SHUXING\" TEXT," + // 7: Monster_shuxing
                "\"WORK_WAYS\" TEXT," + // 8: work_ways
                "\"WORK_MUBIAO\" TEXT," + // 9: work_mubiao
                "\"PTOW_ID\" INTEGER," + // 10: ptow_id
                "\"WORK_CREATETIME\" TEXT);"); // 11: work_createtime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Work entity) {
        stmt.clearBindings();
 
        Long w_id = entity.getW_id();
        if (w_id != null) {
            stmt.bindLong(1, w_id);
        }
        stmt.bindLong(2, entity.getMonster_img());
 
        String Monster_name = entity.getMonster_name();
        if (Monster_name != null) {
            stmt.bindString(3, Monster_name);
        }
 
        String work_name = entity.getWork_name();
        if (work_name != null) {
            stmt.bindString(4, work_name);
        }
        stmt.bindLong(5, entity.getPlan_time());
        stmt.bindLong(6, entity.getMonster_exp());
        stmt.bindLong(7, entity.getMonster_gold());
 
        String Monster_shuxing = entity.getMonster_shuxing();
        if (Monster_shuxing != null) {
            stmt.bindString(8, Monster_shuxing);
        }
 
        String work_ways = entity.getWork_ways();
        if (work_ways != null) {
            stmt.bindString(9, work_ways);
        }
 
        String work_mubiao = entity.getWork_mubiao();
        if (work_mubiao != null) {
            stmt.bindString(10, work_mubiao);
        }
 
        Long ptow_id = entity.getPtow_id();
        if (ptow_id != null) {
            stmt.bindLong(11, ptow_id);
        }
 
        String work_createtime = entity.getWork_createtime();
        if (work_createtime != null) {
            stmt.bindString(12, work_createtime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Work entity) {
        stmt.clearBindings();
 
        Long w_id = entity.getW_id();
        if (w_id != null) {
            stmt.bindLong(1, w_id);
        }
        stmt.bindLong(2, entity.getMonster_img());
 
        String Monster_name = entity.getMonster_name();
        if (Monster_name != null) {
            stmt.bindString(3, Monster_name);
        }
 
        String work_name = entity.getWork_name();
        if (work_name != null) {
            stmt.bindString(4, work_name);
        }
        stmt.bindLong(5, entity.getPlan_time());
        stmt.bindLong(6, entity.getMonster_exp());
        stmt.bindLong(7, entity.getMonster_gold());
 
        String Monster_shuxing = entity.getMonster_shuxing();
        if (Monster_shuxing != null) {
            stmt.bindString(8, Monster_shuxing);
        }
 
        String work_ways = entity.getWork_ways();
        if (work_ways != null) {
            stmt.bindString(9, work_ways);
        }
 
        String work_mubiao = entity.getWork_mubiao();
        if (work_mubiao != null) {
            stmt.bindString(10, work_mubiao);
        }
 
        Long ptow_id = entity.getPtow_id();
        if (ptow_id != null) {
            stmt.bindLong(11, ptow_id);
        }
 
        String work_createtime = entity.getWork_createtime();
        if (work_createtime != null) {
            stmt.bindString(12, work_createtime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Work readEntity(Cursor cursor, int offset) {
        Work entity = new Work( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // w_id
            cursor.getInt(offset + 1), // Monster_img
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Monster_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // work_name
            cursor.getInt(offset + 4), // plan_time
            cursor.getInt(offset + 5), // Monster_exp
            cursor.getInt(offset + 6), // Monster_gold
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Monster_shuxing
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // work_ways
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // work_mubiao
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // ptow_id
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // work_createtime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Work entity, int offset) {
        entity.setW_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMonster_img(cursor.getInt(offset + 1));
        entity.setMonster_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWork_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPlan_time(cursor.getInt(offset + 4));
        entity.setMonster_exp(cursor.getInt(offset + 5));
        entity.setMonster_gold(cursor.getInt(offset + 6));
        entity.setMonster_shuxing(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setWork_ways(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setWork_mubiao(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPtow_id(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setWork_createtime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Work entity, long rowId) {
        entity.setW_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Work entity) {
        if(entity != null) {
            return entity.getW_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Work entity) {
        return entity.getW_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}