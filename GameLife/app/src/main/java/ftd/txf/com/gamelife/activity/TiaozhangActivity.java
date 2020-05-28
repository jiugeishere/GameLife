package ftd.txf.com.gamelife.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dyhdyh.support.countdowntimer.CountDownTimerSupport;
import com.dyhdyh.support.countdowntimer.OnCountDownTimerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.base.BaseActivity;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.record.presenter.RecordPresenter;
import ftd.txf.com.gamelife.utils.IdentityImageView;
import ftd.txf.com.gamelife.utils.WorkDBUtils;
import ftd.txf.com.gamelife.utils.WorksGet;
import pl.droidsonroids.gif.GifDrawable;

public class TiaozhangActivity extends BaseActivity implements RecordContract.View{

    @BindView(R.id.tz_timer)
    IdentityImageView identityImageView;
    @BindView(R.id.tz_time)
    TextView TZTime;
    @BindView(R.id.tz_workname)
    TextView Workname;
    @BindView(R.id.tz_start)
    ImageButton Start;
    @BindView(R.id.tz_back)
    ImageButton Back;
    //presenter类
    private RecordContract.Presenter presenter;
    private Record record;

    private CountDownTimerSupport mTimer;
    private Long id;


    private WorkDBUtils mworkDBUtils;
    private Work work;

    private int i = 0;
    private int angle = 240;

    @Override
    public int intiLayout() {
        return R.layout.activity_tiaozhang;
    }

    @Override
    public void setWindow() {
        setShow(false,false);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        presenter=new RecordPresenter(TiaozhangActivity.this);
        setMVPContext();;
        DealData();

    }


    /**
     * 倒计时结束调用
     */
    private void djsend(){
        Back.setVisibility(View.VISIBLE);
        Start.setVisibility(View.GONE);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("任务完成");
                //计时器停止
                GoMain();
            }
        });
    }

    /**
     * 初始化数据和背景
     */
    private void choosework(){
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
            mworkDBUtils=new WorkDBUtils(TiaozhangActivity.this);
            work=mworkDBUtils.queryWorkById(id);
        }
        String s=work.getWork_ways()+":"+work.getWork_mubiao();
        Workname.setText(work.getWork_name());
        identityImageView.setIsprogress(true);
        identityImageView.setBorderWidth(25);
        identityImageView.setBorderColor(R.color.alpha0);
        identityImageView.setProgressColor(R.color.ghostwhite);
        identityImageView.setRadiusScale(0.1f);
        initTimeView(work.getPlan_time()*60);

    }
    //暂停返回选项
    private void showpauseDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(TiaozhangActivity.this);
        normalDialog.setIcon(work.getMonster_img());
        normalDialog.setCancelable(false);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("此次暂停将进行返回，且不会进行任何数据记录，您确定要放弃吗？");
        normalDialog.setPositiveButton("只是有事",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTimer.pause();
                        GoMain();
                    }
                });
        normalDialog.setNegativeButton("怎么可能",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }
    /**
     * 跳转主活动
     */
    private void GoMain(){
        Intent intent=new Intent();
        intent.setClass(TiaozhangActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void initData() {
        choosework();
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start.setBackgroundResource(R.drawable.tingzhi_64);
                mTimer.start();
            }
        });
    }
    //返回键监听
    @Override
    public void onBackPressed() {
        // super.onBackPressed();//注释掉这行,back键不退出activity

    }
    private void initTimeView(final int time) {
        if (mTimer==null) {
            mTimer = new CountDownTimerSupport(time * 1000, 1000);
            mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
                @Override
                public void onTick(long millisUntilFinished) {
                    //间隔回调
                    long day = millisUntilFinished / (1000 * 24 * 60 * 60);
                    //单位天
                    long hour = (millisUntilFinished - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);
                    //单位时
                    long minute = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60);
                    //单位分
                    long second = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
                    //单位秒
                    String s=hour + ":" + minute + ":" + second;
                    if (minute<9&&second<10){
                        s=hour + ":0" + minute + ":0" + second;
                    }else if (minute>9&&second<10){
                        s=hour + ":" + minute + ":0" + second;
                    }else if (minute<10&&second>9){
                        s=hour + ":0" + minute + ":" + second;
                    }
                    if (hour<9){
                        s="0"+s;
                    }
                    TZTime.setText(s);
                    i++;
                    identityImageView.setProgress((float) i*360/time);
                }

                @Override
                public void onFinish() {
                    //计时器停止
                    mTimer.stop();
                    getDone();
                    djsend();
                }
            });
        }
    }
    //计时完成后调用
    private void getDone(){
        TimeGet timeGet=new TimeGet();
        RecordOne recordOne=new RecordOne();
        recordOne.setCreatime(work.getWork_createtime());
        recordOne.setWork_name(work.getWork_name());
        recordOne.setFinishtime(timeGet.getToMinute());
        recordOne.setTime(work.getPlan_time());
        presenter.setShuxing(work.getMonster_shuxing());
        presenter.setTime(work.getPlan_time());                          //注意先处理time，然后形成的exp和gold
        recordOne.setExp(work.getMonster_exp()*2);
        recordOne.setGold(work.getMonster_gold()*2);
        presenter.addRecordones(recordOne);
        if (id!=-1){
            //删除
            mworkDBUtils.deleteWork(work);
        }
    }
    @Override
    public Context setMVPContext(){
        return TiaozhangActivity.this;
    }
    @Override
    public void DealData(){
        presenter.setContext();
    }
}
