package ftd.txf.com.gamelife.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 成就类
 */
@Entity
public class Chengjiu {

    @Id(autoincrement = true)
    private Long c_id;                //成就id
    private Long chengjiu_id;        //本来是Record一对多关联的，目前暂时未用
    private String chengjiu_name;    //成就名
    private int chengjiu_limit;     //成就限制，一般是达成成就需要的次数，以便进度统计
    private int chengjiu_photo;     //成就的图片，后续不同成就可以考虑用不同图片
    private String chengjiu_style;   //成就风格，就是要达成成就的方式，比如连续登入
    private String chengjiu_time;    //达成成就的时间
    private boolean chengjiu_done;  //是否完成了成就
    private int chengjiu_jingdu;    //成就目前完成的进度，一般会在业务逻辑内更新好加入(1-100)
    @Generated(hash = 2134690987)
    public Chengjiu(Long c_id, Long chengjiu_id, String chengjiu_name, int chengjiu_limit,
            int chengjiu_photo, String chengjiu_style, String chengjiu_time,
            boolean chengjiu_done, int chengjiu_jingdu) {
        this.c_id = c_id;
        this.chengjiu_id = chengjiu_id;
        this.chengjiu_name = chengjiu_name;
        this.chengjiu_limit = chengjiu_limit;
        this.chengjiu_photo = chengjiu_photo;
        this.chengjiu_style = chengjiu_style;
        this.chengjiu_time = chengjiu_time;
        this.chengjiu_done = chengjiu_done;
        this.chengjiu_jingdu = chengjiu_jingdu;
    }

    @Generated(hash = 1050113859)
    public Chengjiu() {
    }

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    public int getChengjiu_jingdu() {
        return chengjiu_jingdu;
    }

    public void setChengjiu_jingdu(int chengjiu_jingdu) {
        this.chengjiu_jingdu = chengjiu_jingdu;
    }

    public int getChengjiu_photo() {
        return chengjiu_photo;
    }

    public void setChengjiu_photo(int chengjiu_photo) {
        this.chengjiu_photo = chengjiu_photo;
    }

    public String getChengjiu_time() {
        return chengjiu_time;
    }

    public void setChengjiu_time(String chengjiu_time) {
        this.chengjiu_time = chengjiu_time;
    }

    public Long getChengjiu_id() {
        return chengjiu_id;
    }

    public void setChengjiu_id(Long chengjiu_id) {
        this.chengjiu_id = chengjiu_id;
    }

    public String getChengjiu_name() {
        return chengjiu_name;
    }

    public void setChengjiu_name(String chengjiu_name) {
        this.chengjiu_name = chengjiu_name;
    }

    public int getChengjiu_limit() {
        return chengjiu_limit;
    }

    public void setChengjiu_limit(int chengjiu_limit) {
        this.chengjiu_limit = chengjiu_limit;
    }

    public String getChengjiu_style() {
        return chengjiu_style;
    }

    public void setChengjiu_style(String chengjiu_style) {
        this.chengjiu_style = chengjiu_style;
    }

    public boolean isChengjiu_done() {
        return chengjiu_done;
    }

    public void setChengjiu_done(boolean chengjiu_done) {
        this.chengjiu_done = chengjiu_done;
    }

    public boolean getChengjiu_done() {
        return this.chengjiu_done;
    }
}
