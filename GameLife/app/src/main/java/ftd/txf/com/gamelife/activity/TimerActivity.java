package ftd.txf.com.gamelife.activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import ftd.txf.com.gamelife.utils.WorkDBUtils;
import ftd.txf.com.gamelife.utils.WorksGet;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * 帧动画
 */

public class TimerActivity extends BaseActivity implements RecordContract.View {

    @BindView(R.id.timer_ways_mubiao)
    TextView Ways_Mubiao;
    @BindView(R.id.timer_time)
    TextView Timer_time;
    @BindView(R.id.timer_control)
    ImageButton Timer_control;
    @BindView(R.id.timer_naozhong)
    ImageView Timer_naoZhong;
    @BindView(R.id.timer_other)
    ImageView Timer_other;
    @BindView(R.id.timer_gifimg)
    GifImageView gifimgview;
    @BindView(R.id.timer_workname)
    TextView workname;
    @BindView(R.id.timer_background)
    LinearLayout timerback;                 //背景
    @BindView(R.id.timer_back)
    ImageView djsback;                      //倒计时返回按钮

    //presenter类
    private RecordContract.Presenter presenter;
    private Record record;

    private GifDrawable gifdrawable;
    private CountDownTimerSupport mTimer;
    private MediaPlayer mplay;
    private int Naozhong_size=0;
    private Long id;


    private WorkDBUtils mworkDBUtils;
    private Work work;
    private Handler zxhandler;
    private int zxTimer=0;

    @Override
    public int intiLayout() {
//设置子类的布局
        return R.layout.activity_timer;
    }

    /**
     * 窗口设置
     */
    @Override
    public void setWindow(){
        setShow(false,false);
    }
    @Override
    public void initView() {
        ButterKnife.bind(this);
        choosegif();
        gifdrawable=(GifDrawable) gifimgview.getDrawable();
        mplay=new MediaPlayer();
        mplay=MediaPlayer.create(TimerActivity.this,R.raw.kanon);
        presenter=new RecordPresenter(TimerActivity.this);
        setMVPContext();;
        DealData();
        gifdrawable.stop();
        TimerClick();
//初始化控件
    }

    /**
     * 选择gif和背景
     */
    private void choosegif(){
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
            mworkDBUtils=new WorkDBUtils(TimerActivity.this);
            work=mworkDBUtils.queryWorkById(id);
        }
        String s=work.getWork_ways()+":"+work.getWork_mubiao();
        Ways_Mubiao.setText(s);
        workname.setText(work.getWork_name());
        switch (work.getWork_mubiao()){
            case "锻炼":
                gifimgview.setImageResource(R.drawable.timer_run);
                timerback.setBackgroundResource(R.color.timer_run);
                break;
            case "学习":
                gifimgview.setImageResource(R.drawable.timer_read);
                timerback.setBackgroundResource(R.color.timer_read);
                break;
            case "拓展":
                gifimgview.setImageResource(R.drawable.timer_tuozhang);
                timerback.setBackgroundResource(R.color.timer_tuozhang);
                break;
        }
    }
    //返回键监听
    @Override
    public void onBackPressed() {
        // super.onBackPressed();//注释掉这行,back键不退出activity
        if (zxTimer==-1){                //zxTimer的双重用法，一、给倒计时做标准 二、本身计时
            toast("任务完成");
            super.onBackPressed();
        }else if(zxTimer==0){
            super.onBackPressed();
        } else {
            showzxOkDialog();
        }
    }
    @Override
    public Context setMVPContext(){
        return TimerActivity.this;
    }
    @Override
    public void DealData(){
        presenter.setContext();
    }

    //控件监听
    private void TimerClick(){
        //闹钟的监控事件
        Timer_naoZhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mplay.isPlaying()){
                    mplay.pause();
                    Timer_naoZhong.setImageResource(R.drawable.no_naozhong_64);
                    toast("暂停铃声");
                    Naozhong_size=2;
                }else if (Naozhong_size==2){
                    mplay.start();
                    Naozhong_size=0;
                    Timer_naoZhong.setImageResource(R.drawable.naozhong_64);
                    toast("播放铃声");
                }else if (Naozhong_size==1){
                    Naozhong_size=0;
                    Timer_naoZhong.setImageResource(R.drawable.naozhong_64);
                    toast("已打开闹钟");
                }else {
                    Naozhong_size=1;
                    Timer_naoZhong.setImageResource(R.drawable.no_naozhong_64);
                    toast("已关闭闹钟");
                }
            }
        });
        Timer_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WayDeal();
            }
        });

        //其他
        Timer_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showotherDialog();
            }
        });
    }

    /**
     * 计时方式处理
     */
    private void WayDeal(){
        int time=inittime();
        switch (work.getWork_ways()){
            case "倒计时":
                initTimeView(time*60);
                if (mTimer.isStart()) {
                    showpauseDialog();
                }else {
                    gifdrawable.start();
                    Timer_control.setBackgroundResource(R.drawable.tingzhi_64);
                    mTimer.start();
                }
                break;
            case "正向计时":
                if (zxTimer==0){
                    gifdrawable.start();
                    Timer_control.setBackgroundResource(R.drawable.tingzhi_64);
                    ZhengTimeView();
                }else {
                    showzxOkDialog();
                }
                break;
        }
    }

    /**
     * 跳转主活动
     */
    private void GoMain(){
        Intent intent=new Intent();
        intent.setClass(TimerActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 多选项功能
     */
    private void showotherDialog() {
        final String[] items = { "进行设备互联","我要听点音乐" ,"开启超强模式","删除该任务","没事"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(TimerActivity.this);
        listDialog.setTitle("其他功能");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                switch (which){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        //删除
                        mworkDBUtils.deleteWork(work);
                        toast("已删除");
                        GoMain();
                        break;
                    case 4:
                        break;
                }
            }
        });
        listDialog.show();
    }
    //暂停返回选项
    private void showpauseDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(TimerActivity.this);
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
    //正向计时选项
    private void showzxOkDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(TimerActivity.this);
        normalDialog.setCancelable(false);
        normalDialog.setIcon(work.getMonster_img());
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("已经统计了"+inittime()+"分钟\n"+"目前获取经验为"+work.getMonster_exp()+"\n确定结束吗？");
        normalDialog.setPositiveButton("收获",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDone();
                        GoMain();
                    }
                });
        normalDialog.setNeutralButton("仅退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GoMain();
            }
        });
        normalDialog.setNegativeButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // 显示
        normalDialog.show();
    }

    @Override
    public void initData() {

    }

    /**
     * 时间返回和计算
     * @return
     */
    private int inittime(){
        int time=zxTimer/60;
        if (work.getWork_mubiao().equals("锻炼")){
            work.setMonster_exp(time*3);
            work.setMonster_gold(time*5);
        }else if (work.getWork_mubiao().equals("学习")){
            work.setMonster_exp(time *2);
            work.setMonster_gold(time*4);
        }else {
            work.setMonster_exp(time);
            work.setMonster_gold(time*2);
        }
        if (work.getWork_ways().equals("正向计时")){
                return time;
        }else {
                return work.getPlan_time();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mplay.stop();

    }

    private void ZhengTimeView(){
            zxhandler=new Handler();
            Runnable mRunnable = new Runnable() {
                @Override
                public void run() {
                    Timer_time.setText(getTime());
                    zxhandler.postDelayed(this, 1000);
                }
            };
            zxhandler.postDelayed(mRunnable,0);
    }
   private String getTime(){
        zxTimer++;
        long second=zxTimer%60;
        long minute=(zxTimer/60)%60;
        long hour=(zxTimer/(60*60))%60;
       String s=hour + ":" + minute + ":" + second;
       if (minute<9&&second<10){
           s=hour + ":0" + minute + ":0" + second;
       }else if (minute>9&&second<10){
           s=hour + ":" + minute + ":0" + second;
       }else if (minute<10&&second>9){
           s=hour + ":0" + minute + ":" + second;
       }
       return s;
   }

    private void initTimeView(int time) {
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
                    Timer_time.setText(s );
                }

                @Override
                public void onFinish() {
                    //计时器停止
                    mTimer.stop();
                    getDone();
                    djsend();
                    zxTimer=-1;
                }
            });
        }
    }

    /**
     * 倒计时结束调用
     */
    private void djsend(){
        djsback.setVisibility(View.VISIBLE);
        Timer_naoZhong.setVisibility(View.GONE);
        Timer_control.setVisibility(View.GONE);
        Timer_other.setVisibility(View.GONE);
        if (Naozhong_size!=1){
            mplay.start();
        }
        djsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("任务完成");
                //计时器停止
                gifdrawable.stop();
                GoMain();
            }
        });
    }
    //计时完成后调用
    private void getDone(){
        TimeGet timeGet=new TimeGet();
        RecordOne recordOne=new RecordOne();
        recordOne.setCreatime(work.getWork_createtime());
        recordOne.setWork_name(work.getWork_name());
        recordOne.setFinishtime(timeGet.getToMinute());
        recordOne.setTime(inittime());
        presenter.setShuxing(work.getMonster_shuxing());
        presenter.setTime(inittime());                          //注意先处理time，然后形成的exp和gold
        recordOne.setExp(work.getMonster_exp());
        recordOne.setGold(work.getMonster_gold());
        presenter.addRecordones(recordOne);
        if (id!=-1){
            //删除
            mworkDBUtils.deleteWork(work);
        }
    }

}
