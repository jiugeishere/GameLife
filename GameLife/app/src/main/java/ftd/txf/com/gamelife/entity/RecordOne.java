package ftd.txf.com.gamelife.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * 单条记录，是每做完一个任务就会生成，
 * 用以数据统计
 */
@Entity
public class RecordOne {
    @Id(autoincrement = true)
    private Long one_id;        //该记录id
    private Long recordone_id; //本来是Record一对多关联的，目前暂时未用

    private String creatime;    //任务的创建时间
    private String finishtime;  //任务完成时间
    private int last_time;
    private String work_name;   //任务名
    private int time;           //任务持续时间
    private int gold;           //获得金币
    private int exp;            //获得经验

    @Generated(hash = 1756860139)
    public RecordOne(Long one_id, Long recordone_id, String creatime, String finishtime,
            int last_time, String work_name, int time, int gold, int exp) {
        this.one_id = one_id;
        this.recordone_id = recordone_id;
        this.creatime = creatime;
        this.finishtime = finishtime;
        this.last_time = last_time;
        this.work_name = work_name;
        this.time = time;
        this.gold = gold;
        this.exp = exp;
    }
    @Generated(hash = 1038043364)
    public RecordOne() {
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Long getOne_id() {
        return one_id;
    }

    public void setOne_id(Long one_id) {
        this.one_id = one_id;
    }

    public Long getRecordone_id() {
        return recordone_id;
    }

    public void setRecordone_id(Long recordone_id) {
        this.recordone_id = recordone_id;
    }

    public String getCreatime() {
        return creatime;
    }

    public void setCreatime(String creatime) {
        this.creatime = creatime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public int getLast_time() {
        return last_time;
    }

    public void setLast_time(int last_time) {
        this.last_time = last_time;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
