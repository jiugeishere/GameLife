package ftd.txf.com.gamelife.entity;

import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Calendar;
import java.util.Date;
import org.greenrobot.greendao.DaoException;
import com.greendaodemo.gen.DaoSession;
import com.greendaodemo.gen.PersonValueDao;
import com.greendaodemo.gen.PersonDao;

/**
 * 用户类
 */
@Entity
public class Person {


    @Id(autoincrement = true)
    private Long person_id;				    //人物id
    private String person_name;			    //人物名称
    private int person_img;					//人物头像

    private int createtime;				    //创建时间
    private int Big_ID;                      //大ID
    private String mubiao;                     //阶段目标
    private int hardrank;                     //难度等级
    private int person_sex;                   //性别
    private String person_geyan;               //格言

    private int persongif;                      //人物形象
    private int congwugif;                      //宠物形象

    private Long personvalue_id;                //value，与用户值PersonValue关联
    @ToOne(joinProperty = "personvalue_id")
    private PersonValue personValue;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 778611619)
    private transient PersonDao myDao;
    @Generated(hash = 1717160305)
    private transient Long personValue__resolvedKey;

    

    @Generated(hash = 1024547259)
    public Person() {
    }

    @Generated(hash = 830136817)
    public Person(Long person_id, String person_name, int person_img, int createtime,
            int Big_ID, String mubiao, int hardrank, int person_sex, String person_geyan,
            int persongif, int congwugif, Long personvalue_id) {
        this.person_id = person_id;
        this.person_name = person_name;
        this.person_img = person_img;
        this.createtime = createtime;
        this.Big_ID = Big_ID;
        this.mubiao = mubiao;
        this.hardrank = hardrank;
        this.person_sex = person_sex;
        this.person_geyan = person_geyan;
        this.persongif = persongif;
        this.congwugif = congwugif;
        this.personvalue_id = personvalue_id;
    }

    public String getPerson_geyan() {
        return person_geyan;
    }

    public void setPerson_geyan(String person_geyan) {
        this.person_geyan = person_geyan;
    }

    public Long getPersonvalue_id() {
        return personvalue_id;
    }

    public void setPersonvalue_id(Long personvalue_id) {
        this.personvalue_id = personvalue_id;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 530859644)
    public PersonValue getPersonValue() {
        Long __key = this.personvalue_id;
        if (personValue__resolvedKey == null
                || !personValue__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonValueDao targetDao = daoSession.getPersonValueDao();
            PersonValue personValueNew = targetDao.load(__key);
            synchronized (this) {
                personValue = personValueNew;
                personValue__resolvedKey = __key;
            }
        }
        return personValue;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 923599001)
    public void setPersonValue(PersonValue personValue) {
        synchronized (this) {
            this.personValue = personValue;
            personvalue_id = personValue == null ? null : personValue.getValue_id();
            personValue__resolvedKey = personvalue_id;
        }
    }

    public int getPersongif() {
        return persongif;
    }

    public void setPersongif(int persongif) {
        this.persongif = persongif;
    }

    public int getCongwugif() {
        return congwugif;
    }

    public void setCongwugif(int congwugif) {
        this.congwugif = congwugif;
    }

    public String getMubiao() {
        return mubiao;
    }

    public void setMubiao(String mubiao) {
        this.mubiao = mubiao;
    }

    public int getHardrank() {
        return hardrank;
    }

    public void setHardrank(int hardrank) {
        this.hardrank = hardrank;
    }

    public int getPerson_sex() {
        return person_sex;
    }

    public void setPerson_sex(int person_sex) {
        this.person_sex = person_sex;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }


    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public int getPerson_img() {
        return person_img;
    }

    public void setPerson_img(int person_img) {
        this.person_img = person_img;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public int getBig_ID() {
        return Big_ID;
    }

    public void setBig_ID(int big_ID) {
        Big_ID = big_ID;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2056799268)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonDao() : null;
    }


}
