package ftd.txf.com.gamelife.entity;

import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户值，用以存储用户方面的各类值
 */
@Entity
public class PersonValue {
    @Id
    private Long value_id;

    private int shuxing_de;					//属性德
    private int shuxing_zhi;					//属性智
    private int shuxing_ti;					//属性体
    private int shuxing_mei;					//属性美
    private int shuxing_lao;					//属性劳

    private int person_rank;					//人物等级
    private int all_exp;					    //人物积累经验
    private int all_gold;					//人物积累金币
    private int Allworld_paihan;            //全服排行
    private int friend_paihan;              //好友排行
    private int worktimes;                   //累积任务数
    private int finally_value_pingfeng;    //最终评分
    private String Chenghao;                    //称号


    @Generated(hash = 1459954680)
    public PersonValue(Long value_id, int shuxing_de, int shuxing_zhi,
            int shuxing_ti, int shuxing_mei, int shuxing_lao, int person_rank,
            int all_exp, int all_gold, int Allworld_paihan, int friend_paihan,
            int worktimes, int finally_value_pingfeng, String Chenghao) {
        this.value_id = value_id;
        this.shuxing_de = shuxing_de;
        this.shuxing_zhi = shuxing_zhi;
        this.shuxing_ti = shuxing_ti;
        this.shuxing_mei = shuxing_mei;
        this.shuxing_lao = shuxing_lao;
        this.person_rank = person_rank;
        this.all_exp = all_exp;
        this.all_gold = all_gold;
        this.Allworld_paihan = Allworld_paihan;
        this.friend_paihan = friend_paihan;
        this.worktimes = worktimes;
        this.finally_value_pingfeng = finally_value_pingfeng;
        this.Chenghao = Chenghao;
    }

    @Generated(hash = 187443407)
    public PersonValue() {
    }

    public Long getValue_id() {
        return value_id;
    }

    public void setValue_id(Long value_id) {
        this.value_id = value_id;
    }

    public int getAll_gold() {
        return all_gold;
    }

    public void setAll_gold(int all_gold) {
        this.all_gold = all_gold;
    }

    public int getShuxing_de() {
        return shuxing_de;
    }

    public void setShuxing_de(int shuxing_de) {
        this.shuxing_de = shuxing_de;
    }

    public int getShuxing_zhi() {
        return shuxing_zhi;
    }

    public void setShuxing_zhi( int shuxing_zhi) {
        this.shuxing_zhi = shuxing_zhi;
    }

    public int getShuxing_ti() {
        return shuxing_ti;
    }

    public void setShuxing_ti( int shuxing_ti) {
        this.shuxing_ti = shuxing_ti;
    }

    public int getShuxing_mei() {
        return shuxing_mei;
    }

    public void setShuxing_mei( int shuxing_mei) {
        this.shuxing_mei = shuxing_mei;
    }

    public int getShuxing_lao() {
        return shuxing_lao;
    }

    public void setShuxing_lao( int shuxing_lao) {
        this.shuxing_lao = shuxing_lao;
    }

    public int getPerson_rank() {
        return person_rank;
    }

    public void setPerson_rank(int person_rank) {
        this.person_rank = person_rank;
    }

    public int getAll_exp() {
        return all_exp;
    }

    public void setAll_exp(int all_exp) {
        this.all_exp = all_exp;
    }


    public int getAllworld_paihan() {
        return Allworld_paihan;
    }

    public void setAllworld_paihan(int allworld_paihan) {
        Allworld_paihan = allworld_paihan;
    }

    public int getFriend_paihan() {
        return friend_paihan;
    }

    public void setFriend_paihan(int friend_paihan) {
        this.friend_paihan = friend_paihan;
    }

    public int getWorktimes() {
        return worktimes;
    }

    public void setWorktimes(int worktimes) {
        this.worktimes = worktimes;
    }

    public int getFinally_value_pingfeng() {
        return finally_value_pingfeng;
    }

    public String getChenghao() {
        return Chenghao;
    }

    public void setChenghao(String chenghao) {
        Chenghao = chenghao;
    }

    public void setFinally_value_pingfeng(int finally_value_pingfeng) {
        this.finally_value_pingfeng = finally_value_pingfeng;
    }

}
