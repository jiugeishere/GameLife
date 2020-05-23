package ftd.txf.com.gamelife.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.greendaodemo.gen.DaoSession;
import com.greendaodemo.gen.RecordDao;
import com.greendaodemo.gen.RecordOneDao;
import com.greendaodemo.gen.ChengjiuDao;

/**
 * 记录类用以存储主要记录
 */
@Entity
public class Record {

    @Id(autoincrement = false)     //非自增，用2L，本地存储一条
    private Long r_id;          //记录id

    private int p_id;           //person id以便云端关联
    private int lastday;        //前一天，与后一天比较看是不是连续登陆
    private int MaxOneDay;      //最大连续时间
    private int MaxWorktime;    //记录上最长工作天数
    private int dayonnoew;      //目前最大连续时间，与MaxWorktime进行比较，如果比MaxWorktime高则更新Maxworktime
    private int study_times;     //记载学习类型任务次数
    private int excise_times;    //记载锻炼类型任务次数
    private int extend_times;    //记载拓展类型任务次数
    private int alltime;          //记录总时间
    private int morebiaozhi;     //More标准，是我用来偷巧跳转传值的方法（Fragment->Activity）


    @Generated(hash = 1326690091)
    public Record(Long r_id, int p_id, int lastday, int MaxOneDay, int MaxWorktime, int dayonnoew,
            int study_times, int excise_times, int extend_times, int alltime, int morebiaozhi) {
        this.r_id = r_id;
        this.p_id = p_id;
        this.lastday = lastday;
        this.MaxOneDay = MaxOneDay;
        this.MaxWorktime = MaxWorktime;
        this.dayonnoew = dayonnoew;
        this.study_times = study_times;
        this.excise_times = excise_times;
        this.extend_times = extend_times;
        this.alltime = alltime;
        this.morebiaozhi = morebiaozhi;
    }
    @Generated(hash = 477726293)
    public Record() {
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getLastday() {
        return lastday;
    }

    public void setLastday(int lastday) {
        this.lastday = lastday;
    }

    public int getMaxOneDay() {
        return MaxOneDay;
    }

    public void setMaxOneDay(int maxOneDay) {
        MaxOneDay = maxOneDay;
    }

    public int getMaxWorktime() {
        return MaxWorktime;
    }

    public void setMaxWorktime(int maxWorktime) {
        MaxWorktime = maxWorktime;
    }


    public Long getR_id() {
        return this.r_id;
    }
    public void setR_id(Long r_id) {
        this.r_id = r_id;
    }

    public int getDayonnoew() {
        return dayonnoew;
    }

    public void setDayonnoew(int daydate) {
        this.dayonnoew = daydate;
    }

    public int getStudy_times() {
        return study_times;
    }

    public void setStudy_times(int study_times) {
        this.study_times = study_times;
    }

    public int getExcise_times() {
        return excise_times;
    }

    public void setExcise_times(int excise_times) {
        this.excise_times = excise_times;
    }

    public int getExtend_times() {
        return extend_times;
    }

    public void setExtend_times(int extend_times) {
        this.extend_times = extend_times;
    }

    public int getAlltime() {
        return alltime;
    }

    public void setAlltime(int alltime) {
        this.alltime = alltime;
    }

    public int getMorebiaozhi() {
        return morebiaozhi;
    }

    public void setMorebiaozhi(int morebiaozhi) {
        this.morebiaozhi = morebiaozhi;
    }
}
