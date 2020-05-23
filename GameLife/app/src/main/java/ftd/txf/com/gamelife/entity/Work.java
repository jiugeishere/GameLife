package ftd.txf.com.gamelife.entity;


import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

import java.util.Date;

/**
 * 任务类
 */
@Entity
public class Work {
    @Id(autoincrement = true)
    private Long w_id;
    private int Monster_img;			//怪物图片
    private String Monster_name;	    //怪物名
    private String work_name;			//任务名
    private int plan_time;			//计划时间
    private int Monster_exp;			//怪物经验
    private int Monster_gold;	    //怪物金币
    private String Monster_shuxing; //怪物属性
    private String work_ways;			//计划方式
    private String work_mubiao;		//计划目标
    private Long ptow_id;             //本来是准备与Person关联进行云存储，目前未用，还在考虑中
    private String work_createtime;    //创建时间

    @Generated(hash = 724925269)
    public Work(Long w_id, int Monster_img, String Monster_name, String work_name,
            int plan_time, int Monster_exp, int Monster_gold, String Monster_shuxing,
            String work_ways, String work_mubiao, Long ptow_id, String work_createtime) {
        this.w_id = w_id;
        this.Monster_img = Monster_img;
        this.Monster_name = Monster_name;
        this.work_name = work_name;
        this.plan_time = plan_time;
        this.Monster_exp = Monster_exp;
        this.Monster_gold = Monster_gold;
        this.Monster_shuxing = Monster_shuxing;
        this.work_ways = work_ways;
        this.work_mubiao = work_mubiao;
        this.ptow_id = ptow_id;
        this.work_createtime = work_createtime;
    }
    @Generated(hash = 572069219)
    public Work() {
    }
    public int getMonster_gold() {
        return Monster_gold;
    }

    public void setMonster_gold(int monster_gold) {
        Monster_gold = monster_gold;
    }

    public String getMonster_shuxing() {
        return Monster_shuxing;
    }

    public void setMonster_shuxing(String monster_shuxing) {
        Monster_shuxing = monster_shuxing;
    }
    public String getWork_ways() {
        return work_ways;
    }

    public void setWork_ways(String work_ways) {
        this.work_ways = work_ways;
    }

    public String getWork_mubiao() {
        return work_mubiao;
    }

    public void setWork_mubiao(String work_mubiao) {
        this.work_mubiao = work_mubiao;
    }

    public int getMonster_img() {
        return Monster_img;
    }

    public void setMonster_img(int monster_img) {
        Monster_img = monster_img;
    }

    public String getMonster_name() {
        return Monster_name;
    }

    public void setMonster_name(String monster_name) {
        Monster_name = monster_name;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public int getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(int plan_time) {
        this.plan_time = plan_time;
    }

    public int getMonster_exp() {
        return Monster_exp;
    }

    public void setMonster_exp(int monster_exp) {
        Monster_exp = monster_exp;
    }
    public Long getW_id() {
        return this.w_id;
    }
    public void setW_id(Long w_id) {
        this.w_id = w_id;
    }



    public Long getPtow_id() {
        return ptow_id;
    }

    public String getWork_createtime() {
        return work_createtime;
    }

    public void setWork_createtime(String work_createtime) {
        this.work_createtime = work_createtime;
    }

    public void setPtow_id(Long ptow_id) {
        this.ptow_id = ptow_id;
    }
    @Override
    public String toString(){
        return "Work{" +
                "W_id=" + w_id +
                ", Monster_img='" + Monster_img + '\'' +
                ", Monster_name='" + Monster_name + '\'' +
                ", Work_name='" + work_name + '\'' +
                ", Plan_time='" + plan_time + '\'' +
                ", Monster_exp='" + Monster_exp + '\'' +
                '}';
    }
    public String getContent(){
        String s="时间:"+plan_time+"分钟    金币:"+Monster_gold+"    获取属性:"+Monster_shuxing;
        return s;
    }

}
