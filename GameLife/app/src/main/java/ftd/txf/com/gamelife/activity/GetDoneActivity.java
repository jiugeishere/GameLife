package ftd.txf.com.gamelife.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.base.BaseActivity;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.record.presenter.RecordPresenter;
import ftd.txf.com.gamelife.utils.WorkDBUtils;
import ftd.txf.com.gamelife.utils.WorksGet;

public class GetDoneActivity extends BaseActivity implements RecordContract.View {

    @BindView(R.id.trans_huojian)
    ImageView huojian;
    @BindView(R.id.Getdone_button)
    Button getdon_button;
    @BindView(R.id.getdon_day)
    TextView GetDay;
    @BindView(R.id.getdon_month)
    TextView GetMonth;
    @BindView(R.id.getdon_week)
    TextView GetWeek;
    @BindView(R.id.getdon_workname)
    TextView GetWorkname;
    private Long id;


    private WorkDBUtils mworkDBUtils;
    private Work work;
    //presenter类
    private RecordContract.Presenter presenter;

    public int intiLayout(){
        return R.layout.activity_get_done;
    }
    public void setWindow(){
        setShow(false,false);
    }

    /**
     * 初始化布局
     */
    public void initView(){
        ButterKnife.bind(this);
        presenter=new RecordPresenter(GetDoneActivity.this);
        TimeGet timeGet=new TimeGet();
        GetDay.setText(timeGet.getDayS(timeGet.getDay()));
        String s=timeGet.getYear()+"."+timeGet.getMonth();
        GetMonth.setText(s);
        GetWeek.setText(timeGet.getFormatWeek());
        setMVPContext();;
        DealData();
        getdon_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDone();
                show_action();
            }
        });
    }
    private void show_action(){
        TranslateAnimation translateAnimation1 = new TranslateAnimation(0,0,0,-1000);
        translateAnimation1.setDuration(3000);
        translateAnimation1.setInterpolator(new DecelerateInterpolator());
        translateAnimation1.setFillAfter(true);
        translateAnimation1.setStartOffset(1000);
        translateAnimation1.setRepeatCount(0);
        huojian.startAnimation(translateAnimation1);
        translateAnimation1.startNow();
        translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                getdon_button.setAlpha(0.5f);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toast("打卡完成");
                GoMain();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    /**
     * 设置数据
     */

    @Override
    public Context setMVPContext(){
        return GetDoneActivity.this;
    }
    @Override
    public void DealData(){
        presenter.setContext();
    }
    public void initData(){
        Intent intent=getIntent();
        id=intent.getLongExtra("Data_id",-1);
        toast(id.toString());
        if (id==-1){
            WorksGet worksGet=new WorksGet();
            List<Work> listworks=new ArrayList<>();
            listworks=worksGet.getXiTongdata(4);
            int postion=intent.getIntExtra("XT_postion",-1);
            work=listworks.get(postion);
        }else {
            mworkDBUtils=new WorkDBUtils(GetDoneActivity.this);
            work=mworkDBUtils.queryWorkById(id);
        }
        GetWorkname.setText(work.getWork_name());

    }
    /**
     * 跳转主活动
     */
    private void GoMain(){
        Intent intent=new Intent();
        intent.setClass(GetDoneActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    //打卡完成后调用
    private void getDone(){
        TimeGet timeGet=new TimeGet();
        RecordOne recordOne=new RecordOne();
        recordOne.setCreatime(work.getWork_createtime());
        recordOne.setExp(work.getMonster_exp());
        recordOne.setGold(work.getMonster_gold());
        recordOne.setTime(60);
        recordOne.setWork_name(work.getWork_name());
        recordOne.setFinishtime(timeGet.getToMinute());
        presenter.setShuxing(work.getMonster_shuxing());
        presenter.setTime(60);
        presenter.addRecordones(recordOne);
        if (id!=-1){
            //删除
            mworkDBUtils.deleteWork(work);
        }
    }
}
