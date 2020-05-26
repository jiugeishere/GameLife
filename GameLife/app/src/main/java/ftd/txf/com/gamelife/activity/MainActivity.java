package ftd.txf.com.gamelife.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.base.BaseFragment;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.fragment.MineFragment;
import ftd.txf.com.gamelife.fragment.ChatFragment;
import ftd.txf.com.gamelife.record.RecordFragment;
import ftd.txf.com.gamelife.utils.MyNumberPicker;
import ftd.txf.com.gamelife.work.WorkFragment;
import ftd.txf.com.gamelife.work.contract.WorkContract;
import ftd.txf.com.gamelife.work.presenter.WorkPresenter;

public class MainActivity extends FragmentActivity implements WorkContract.View {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.tabs_rg)
    RadioGroup rgMain;
    @BindView(R.id.add_floatButton)
    FloatingActionButton fabButton;


    //Presenter类
    private WorkContract.Presenter presenter;
    //装fragment的实例集合
    private ArrayList<BaseFragment> fragments;

    private int position = 0;
    //时间设置
    private int hour=0;
    private int minute=0;
    //方式设计
    private String ways="倒计时";
    private String mubiao="锻炼";

    private int expint=0;
    private int goldint=0;
    //缓存Fragment或上次显示的Fragment
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);

        presenter=new WorkPresenter(this);
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        updateView();
        //根据位置得到相应的Fragment
        BaseFragment baseFragment = getFragment(position);
        switchFragment(tempFragment,baseFragment);
        initListener();
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        Intent intent=getIntent();
        int fragment_id=intent.getIntExtra("Fragment",-1);
        if (fragment_id==4){
            rgMain.check(R.id.mine_tab);
        }else if (fragment_id==2){
            rgMain.check(R.id.record_tab);
        }
    }

    @Override
    public void saveWork(Work work){
        presenter.sendWorkdata(this);
        presenter.saveWork(work);
    }
    @Override
    public void updateView(){
//        workList=presenter.sendWorkdata(MainActivity.this);
    }


    private void initListener() {
        rgMain.check(R.id.work_tab);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.work_tab: //任务
                        position = 0;
                        break;
                    case R.id.record_tab: //记录
                        position = 1;
                        break;
                    case R.id.plan_tab: //计划
                        position = 2;
                        break;
                    case R.id.mine_tab: //我的
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置得到相应的Fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一个参数: 上次显示的Fragment
                 * 第二个参数: 当前正要显示的Fragment
                 */
                switchFragment(tempFragment,baseFragment);
            }
        });
    }
    /**
     * 添加的时候按照顺序
     */
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new WorkFragment());
        fragments.add(new RecordFragment());
        fragments.add(new ChatFragment());
        fragments.add(new MineFragment());
    }
    /**
     * 根据位置得到对应的 Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position){
        if(fragments != null && fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     * @param fragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fragment,BaseFragment nextFragment){
        if (tempFragment != nextFragment){
            tempFragment = nextFragment;
            if (nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded()){
                    //隐藏当前的Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else {
                    //隐藏当前Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    /**
     * 刷新当前fragment，通过删除后添加的方式
     */
    private void refresh_fragment(){
            hour=0;
            minute=0;
            expint=0;
            goldint=0;
            mubiao="锻炼";//进行数据清空，避免上次添加数据影响到下次
            ways="倒计时";
            initFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(tempFragment);
            tempFragment=fragments.get(position);
            transaction.add(R.id.frameLayout,tempFragment);
            transaction.show(tempFragment).commitNow();
    }
    private void exp_gold_calute(String mubiao,String ways){//经验金币逻辑计算，以便快速显示
        if (ways.equals("不计时")){
            expint=120;
            goldint=30;
        }else {
            switch (mubiao){
                case "锻炼":
                    expint=(hour*60+minute)*3;
                    goldint=(hour*60+minute)*5;
                    break;
                case "学习":
                    expint=(hour*60+minute)*2;
                    goldint=(hour*60+minute)*4;
                    break;
                case "拓展":
                    expint=(hour*60+minute)*1;
                    goldint=(hour*60+minute)*2;
                    break;
            }
        }
    }
    //初始化并弹出对话框方法
    private void showDialog(){
        final View dialogview = LayoutInflater.from(this).inflate(R.layout.work_dialog,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogview).create();
        FloatingActionButton acceptbutton=(FloatingActionButton) dialogview.findViewById(R.id.workdialog_accept);
        ImageView cancel=(ImageView)dialogview.findViewById(R.id.img_cancel);
        //任务名
        final EditText workname=(EditText)dialogview.findViewById(R.id.dialog_workname);
        //参数
        final TextView exp=(TextView)dialogview.findViewById(R.id.dialog_exp);
        final TextView gold=(TextView)dialogview.findViewById(R.id.dialog_gold);
        final TextView shuxing=(TextView)dialogview.findViewById(R.id.dialog_shuxing);
        //目标绑定
        final RadioGroup Rgmb=(RadioGroup)dialogview.findViewById(R.id.rg_mb);
        final RadioButton mb_excise=(RadioButton)dialogview.findViewById(R.id.rb_mb_excise);
        final RadioButton mb_other=(RadioButton)dialogview.findViewById(R.id.rb_mb_other);
        final RadioButton mb_study=(RadioButton)dialogview.findViewById(R.id.rb_mb_study);
        //类型绑定
        RadioGroup Rgways=(RadioGroup)dialogview.findViewById(R.id.rg_ways);
        final RadioButton ways_djs=(RadioButton)dialogview.findViewById(R.id.rg_ways_djs);
        final RadioButton ways_zxjs=(RadioButton)dialogview.findViewById(R.id.rg_ways_zxjs);
        final RadioButton ways_bjs=(RadioButton)dialogview.findViewById(R.id.rg_ways_bjs);
        //时间绑定
        MyNumberPicker hourPicker = (MyNumberPicker) dialogview.findViewById(R.id.dialog_hourpicker);
        final MyNumberPicker minutePicker = (MyNumberPicker) dialogview.findViewById(R.id.dialog_minutepicker);

        //设置时钟
        //设置最大值
        hourPicker.setMaxValue(5);
        //设置最小值
        hourPicker.setMinValue(0);
        //设置当前值
        hourPicker.setValue(0);
        //关闭编辑模式
        hourPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        //分割线颜色
        setNumberPickerDividerColor(hourPicker);
        //设置滑动监听
        hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            //当NunberPicker的值发生改变时，将会激发该方法
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                hour=newVal;
                exp_gold_calute(mubiao,ways);
                exp.setText(String.valueOf(expint));
                gold.setText(String.valueOf(goldint));

            }
        });
        //设置分钟
        minutePicker.setMaxValue(59);
        minutePicker.setMinValue(0);
        minutePicker.setValue(0);
        minutePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(minutePicker);
        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            //当NunberPicker的值发生改变时，将会激发该方法
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minute=newVal;
                exp_gold_calute(mubiao,ways);
                exp.setText(String.valueOf(expint));
                gold.setText(String.valueOf(goldint));
            }
        });
        //目标类型设计
        Rgmb.check(R.id.rb_mb_excise);
        Rgmb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.rb_mb_excise:
                        mubiao=mb_excise.getText().toString();
                        shuxing.setText("体");
                        break;
                    case R.id.rb_mb_study:
                        mubiao=mb_study.getText().toString();
                        shuxing.setText("智");
                        break;
                    case R.id.rb_mb_other:
                        mubiao=mb_other.getText().toString();
                        shuxing.setText("德");
                        break;
                }
                exp_gold_calute(mubiao,ways);
                exp.setText(String.valueOf(expint));
                gold.setText(String.valueOf(goldint));
            }
        });
        //计时方式设计
        Rgways.check(R.id.rg_ways_djs);
        Rgways.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.rg_ways_djs:
                        ways=ways_djs.getText().toString();
                        break;
                    case R.id.rg_ways_zxjs:
                        ways=ways_zxjs.getText().toString();
                        Toast.makeText(MainActivity.this,"设置时间将为参考，不影响结果计算", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rg_ways_bjs:
                        ways=ways_bjs.getText().toString();
                        Toast.makeText(MainActivity.this,"设置时间将为参考，不影响结果计算", Toast.LENGTH_SHORT).show();
                        break;
                }
                exp_gold_calute(mubiao,ways);
                exp.setText(String.valueOf(expint));
                gold.setText(String.valueOf(goldint));
            }
        });

        //确认按钮
        acceptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int plantime=hour*60+minute;
                TimeGet timeGet=new TimeGet();
                Work work=new Work(null,R.drawable.monster_renzheng,"认真怪",workname.getText().toString(),plantime,300,150,shuxing.getText().toString(),ways,mubiao,2L,timeGet.getToMinute());
                String workname_test=workname.getText().toString().trim();
                if (workname_test.isEmpty()||workname_test.length()==0){
                    Toast.makeText(MainActivity.this,"请输入任务内容",Toast.LENGTH_SHORT).show();
                    return;
                }else if (work.getWork_ways().equals("倒计时")&&work.getPlan_time()==0){
                    Toast.makeText(MainActivity.this,"倒计时任务时间不可为零",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    saveWork(work);
                }
                refresh_fragment();
                dialog.cancel();

            }
        });
        //取消按钮
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        //此处设置位置窗体大小
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha0);
    }

    /**
     * 自定义滚动框分隔线颜色
     */
    private void setNumberPickerDividerColor(NumberPicker number) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(number, new ColorDrawable(ContextCompat.getColor(this, R.color.alpha0)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}