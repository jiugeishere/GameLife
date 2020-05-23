package ftd.txf.com.gamelife.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.base.BaseActivity;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.limitutils.MobileInfoUtils;
import ftd.txf.com.gamelife.services.RemindActionService;

public class WorkMoreActivity extends BaseActivity {

    @BindView(R.id.workmore_choosenaozhong)
    Switch ChooseNaozhong;
    @BindView(R.id.workmore_choosetime)
    LinearLayout ChooseTime;
    @BindView(R.id.workmore_choosecontent)
    LinearLayout ChooseContent;
    @BindView(R.id.workmore_timetext)
    TextView Timetext;
    @BindView(R.id.workmore_content)
    TextView Contenttext;
    @BindView(R.id.workmore_quanxian)
    LinearLayout Quanxian;
    @BindView(R.id.workmore_musicshow)
    LinearLayout MusicShow;
    @BindView(R.id.workmore_musictext)
    TextView MusicText;
    @BindView(R.id.workmore_nzup)
    ImageButton MusicUp;
    @BindView(R.id.workmore_music1)
    ImageButton Music1;
    @BindView(R.id.workmore_music2)
    ImageButton Music2;
    @BindView(R.id.workmore_music3)
    ImageButton Music3;
    @BindView(R.id.workmore_musicshow1)
    ImageView MusicShow1;
    @BindView(R.id.workmore_musicshow2)
    ImageView MusicShow2;
    @BindView(R.id.workmore_musicshow3)
    ImageView MusicShow3;

    //内容和时间
    private String content="";
    private int hour=0;
    private int minte=0;
    //音乐选择
    private  Animation rotateAnimation;
    private int showmusic=1;
    private int music=0;
    //设置存储
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    public int intiLayout() {
        return R.layout.activity_work_more;
    }

    @Override
    public void setWindow() {
        setShow(false,true);
    }

    @Override
    public void initView() {
        ButterKnife.bind(WorkMoreActivity.this);
        sharedPreferences=getSharedPreferences("setting",MODE_PRIVATE);
        int c=sharedPreferences.getInt("naocheck",0);
        hour=sharedPreferences.getInt("naohour",14);
        minte=sharedPreferences.getInt("naominute",0);
        content=sharedPreferences.getString("naocontent","请务必提醒我");
        if (minte<10){
            Timetext.setText(String.valueOf(hour+":0"+minte));
        }else {
            Timetext.setText(String.valueOf(hour+":"+minte));
        }
        Contenttext.setText(content);
        if (c==1){
            ChooseNaozhong.setChecked(true);
        }
        editor=sharedPreferences.edit();
        naozhong();
    }

    @Override
    public void initData() {
        setMusic();
        setQuanxian();
    }

    /**
     * 音乐设置
     */
    private void setMusic(){
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnimation.setInterpolator(lin);
        MusicUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showmusic!=0){
                    MusicUp.setImageResource(R.drawable.up);
                    MusicShow.setVisibility(View.GONE);
                    showmusic=0;
                }else {
                    showmusic=1;
                    MusicUp.setImageResource(R.drawable.down);
                    MusicShow.setVisibility(View.VISIBLE);
                }
            }
        });
        Music1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicshow();
                if (music!=1){
                    music=1;
                    MusicShow1.startAnimation(rotateAnimation);
                    Music1.setImageResource(R.drawable.pause_black);
                }else {
                    musicshow();
                    music=-1;
                }
                MusicText.setText("闹钟1");
                showmusic=2;
            }
        });
        Music2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicshow();
                if (music!=2){
                    music=2;
                    MusicShow2.startAnimation(rotateAnimation);
                    Music2.setImageResource(R.drawable.pause_black);
                }else {
                    musicshow();
                    music=-2;
                }
                MusicText.setText("闹钟2");
                showmusic=3;
            }
        });
        Music3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicshow();
                if (music!=3){
                    music=3;
                    MusicShow3.startAnimation(rotateAnimation);
                    Music3.setImageResource(R.drawable.pause_black);
                }else {
                    musicshow();
                    music=-3;
                }
                MusicText.setText("闹钟3");
                    showmusic=4;

            }
        });
    }
    private void musicshow(){
        switch (showmusic){
            case 2:
                MusicShow1.clearAnimation();
                Music1.setImageResource(R.drawable.play_black);
                break;
            case 3:
                MusicShow2.clearAnimation();
                Music2.setImageResource(R.drawable.play_black);
                break;
            case 4:
                MusicShow3.clearAnimation();
                Music3.setImageResource(R.drawable.play_black);
                break;
            default:
                break;
        }
        showmusic=1;

    }
    /**
     * 设置权限
     */
    private void setQuanxian(){
        Quanxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileInfoUtils mobileInfoUtils=new MobileInfoUtils();
                mobileInfoUtils.jumpStartInterface(WorkMoreActivity.this);
            }
        });
    }
    /**
     * 发送信息
     * @param f
     */
    private void sendmessage(boolean f){
        TimeGet timeGet=new TimeGet();
        int time=timeGet.TimetoNextDay(hour,minte);
        long start = System.currentTimeMillis()+time*1000;
        Intent intent = new Intent(WorkMoreActivity.this, RemindActionService.class);
        intent.putExtra("channel", R.layout.activity_work_more);
        intent.putExtra("title", "游戏人生");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getService(WorkMoreActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am= (AlarmManager) WorkMoreActivity.this.getSystemService(Context.ALARM_SERVICE);
        if (f){
            if (!isIgnoringBatteryOptimizations()){
                requestIgnoreBatteryOptimizations();
            }
               am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,start,AlarmManager.INTERVAL_DAY,pendingIntent);
        }else {
            am.cancel(pendingIntent);
        }
        editor.putInt("naohour",hour);
        editor.putInt("naominute",minte);
        editor.putString("naocontent",content);
        editor.commit();
    }
    private void naozhong(){
        if (!ChooseNaozhong.isChecked()){
            ChooseContent.setVisibility(View.GONE);
            ChooseTime.setVisibility(View.GONE);
        }
        ChooseNaozhong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ChooseContent.setVisibility(View.VISIBLE);
                    ChooseTime.setVisibility(View.VISIBLE);
                    sendmessage(true);
                    editor.putInt("naocheck",1);
                }else {
                    ChooseContent.setVisibility(View.GONE);
                    ChooseTime.setVisibility(View.GONE);
                    sendmessage(false);
                    editor.putInt("naocheck",0);
                }
            }
        });
        ChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TimeGet timeGet=new TimeGet();
                TimePickerDialog timePickerDialog = new TimePickerDialog(WorkMoreActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (minute<10){
                                    Timetext.setText(String.valueOf(hourOfDay+":0"+minute));
                                }else {
                                    Timetext.setText(String.valueOf(hourOfDay+":"+minute));
                                }
                                hour=hourOfDay;
                                minte=minute;
                                sendmessage(true);
                            }
                        }, timeGet.getHour(), timeGet.getMinute(), true);
                timePickerDialog.show();
            }
        });
        ChooseContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_edit();
            }
        });
    }

    /**
     * 对话框
     */
    public void alert_edit(){
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(WorkMoreActivity.this);
        final AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(WorkMoreActivity.this);
        inputDialog.setCancelable(false);
        inputDialog.setTitle("输入提醒内容").setView(editText);
        inputDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        content=editText.getText().toString();
                        Contenttext.setText(content);
                        Toast.makeText(WorkMoreActivity.this,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }


    /**
     * 获取自启动权限
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(getPackageName());
        }
        return isIgnoring;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
