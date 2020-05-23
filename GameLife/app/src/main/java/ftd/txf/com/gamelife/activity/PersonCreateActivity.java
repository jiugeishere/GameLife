package ftd.txf.com.gamelife.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.base.BaseActivity;
import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.utils.DBUtils;

public class PersonCreateActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.personcreate_button)
    Button person_button;
    @BindView(R.id.personcreate_name)
    EditText person_name;
    @BindView(R.id.personcreate_geyan)
    EditText person_Geyan;
    @BindView(R.id.personcreate_back)
    ImageView person_back;
    @BindView(R.id.personcreate_seximg)
    ImageView person_sex;
    @BindView(R.id.personcreate_touxiang)
    ImageView person_touxiang;
    @BindView(R.id.personcreate_1)
    ImageButton select_1;
    @BindView(R.id.personcreate_2)
    ImageButton select_2;
    @BindView(R.id.personcreate_3)
    ImageButton select_3;
    @BindView(R.id.personcreate_4)
    ImageButton select_4;
    @BindView(R.id.personcreate_5)
    ImageButton select_5;
    @BindView(R.id.personcreate_6)
    ImageButton select_6;
    @BindView(R.id.person_toolbar)
    Toolbar person_toolbar;

    private List<Boolean> list=Arrays.asList(false,false,false);
    private int hard=0;
    private int sex=1;
    private int f_sex=1;
    private DBUtils mDButils;
    private Person person;


    @Override
    public int intiLayout() {
        return R.layout.activity_person_create;
    }

    @Override
    public void setWindow(){
        setShow(false,false);
    }
    /**
     * 绑定监听
     */
    @Override
    public void initView() {
        ButterKnife.bind(this);
         createPerson();
         selectview();
         person_touxiang.setOnClickListener(this);
         person_button.setOnClickListener(this);
         person_back.setOnClickListener(this);
         person_sex.setOnClickListener(this);
         select_1.setOnClickListener(this);
         select_2.setOnClickListener(this);
         select_3.setOnClickListener(this);
         select_4.setOnClickListener(this);
         select_5.setOnClickListener(this);
         select_6.setOnClickListener(this);

    }
    /**
     * 第一次登陆判断
     */
    private void createPerson(){
        mDButils=new DBUtils(this);
        if (mDButils.queryPersonById(1L)==null){
            TimeGet timeGet=new TimeGet();
            person=new Person(1L,"",R.drawable.touxiang1,timeGet.getToDay(),timeGet.getBigID(),"学",1,1,"",R.drawable.personshow1,R.drawable.congwu2,1L);
            person_toolbar.setVisibility(View.GONE);
            Record record=new Record(2L,person.getBig_ID(),timeGet.getToDay()-1,0,0,0,0,0,0,0,0);
            PersonValue personValue=new PersonValue(1L,0,0,0,0,0,0,0,0,0,1,0,0,"无");
            mDButils.insertMultChengjiu(createChengjiu(record.getR_id()));
            mDButils.insertPersonValue(personValue);
            mDButils.insertRecord(record);
            mDButils.insertPerson(person);
            Log.i("personcreaterror:","未创建用户");
        }else {
            person=mDButils.queryPersonById(1L);
            Log.i("personcreaterror:","已创建用户"+person.getPerson_name());
            GoMain();
        }
    }
    private List<Chengjiu> createChengjiu(Long id){
        List<Chengjiu> chengjiuLists=new ArrayList<Chengjiu>();
        chengjiuLists.add(new Chengjiu(null,id,"万事开头难",1,R.drawable.chengjiu_jiangno,"天连续使用计时功能","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"锲而不舍",7,R.drawable.chengjiu_jiangno,"天连续使用计时功能","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"长征精神",14,R.drawable.chengjiu_jiangno,"天连续使用计时功能","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"已成大器",21,R.drawable.chengjiu_jiangno,"天连续使用计时功能","",false,0));

        chengjiuLists.add(new Chengjiu(null,id,"初学者",5,R.drawable.chengjiu_jiangno,"次学习","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"学而时习之",30,R.drawable.chengjiu_jiangno,"次学习","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"不亦乐乎",60,R.drawable.chengjiu_jiangno,"次学习","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"学海无涯",100,R.drawable.chengjiu_jiangno,"次学习","",false,0));

        chengjiuLists.add(new Chengjiu(null,id,"战胜疲惫",5,R.drawable.chengjiu_jiangno,"次锻炼","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"精神抖擞",30,R.drawable.chengjiu_jiangno,"次锻炼","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"肌肉猛男",60,R.drawable.chengjiu_jiangno,"次锻炼","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"只要一拳",100,R.drawable.chengjiu_jiangno,"次锻炼","",false,0));

        chengjiuLists.add(new Chengjiu(null,id,"试探一下",5,R.drawable.chengjiu_jiangno,"次拓展","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"小意思啦",30,R.drawable.chengjiu_jiangno,"次拓展","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"不知不觉",60,R.drawable.chengjiu_jiangno,"次拓展","",false,0));
        chengjiuLists.add(new Chengjiu(null,id,"超强自律",100,R.drawable.chengjiu_jiangno,"次拓展","",false,0));
        return chengjiuLists;
    }
    /**
     * 已经登陆过的数据恢复
     */
    private void selectview(){
        String s_mubiao=person.getMubiao();
        for (int i=0;i<s_mubiao.length();i++){
            switch (s_mubiao.substring(i,i+1)){
                case "学":
                    list.set(0,true);
                    select_1.setBackgroundResource(R.drawable.select_right_tian);
                    break;
                case "锻":
                    list.set(1,true);
                    select_2.setBackgroundResource(R.drawable.select_right_tian);
                    break;
                case "拓":
                    list.set(2,true);
                    select_3.setBackgroundResource(R.drawable.select_right_tian);
                    break;
            }
        }

        int h=person.getHardrank();
        hard=h;
        switch (h){
            case 0:
                break;
            case 1:
                select_4.setBackgroundResource(R.drawable.select_right_tian);
                break;
            case 2:
                select_5.setBackgroundResource(R.drawable.select_right_tian);
                break;
            case 3:
                select_6.setBackgroundResource(R.drawable.select_right_tian);
                break;
        }
        person_name.setText(person.getPerson_name());
        f_sex=person.getPerson_sex();
        sex=f_sex;
        choosesex();
        person_Geyan.setText(person.getPerson_geyan());
    }
    @Override
    public void initData() {

    }
    /**
     * 进行目标类的文本集合，后续有时间改成int类
     */
    private String getmubiao(){
        String s="";
        if (list.get(0)){
            s=s+"学";
        }
        if (list.get(1)) {
            s=s+"锻";
        }
        if (list.get(2)){
            s=s+"拓";
        }
        return s;
    }
    /**
     * 点击事件
     */
    private void GoMain(){
        Intent intent=new Intent();
        intent.setClass(PersonCreateActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.personcreate_back:
                GoMain();
            case R.id.personcreate_button:                          //确认按钮
                if (person_name.getText().toString().equals("")){
                    toast("请输入名字");
                    return;
                }
                person.setPerson_name(person_name.getText().toString());
                person.setPerson_geyan(person_Geyan.getText().toString());
                if (!(list.get(0)||list.get(1)||list.get(2))){
                    toast("请选择目标");
                    return;
                }
                if (hard==0){
                    toast("请选择难度");
                    return;
                }                                                    //不符合规范判断
                person.setPerson_sex(f_sex);
                person.setMubiao(getmubiao());
                person.setHardrank(hard);

                mDButils.insertPerson(person);
                toast("已更新");
                GoMain();
                break;
            case R.id.personcreate_touxiang:                          //头像替换，xml用的水平ScrollView
                showtouxiangDialog();
                break;
            case R.id.personcreate_seximg:                            //性别选定
                choosesex();
                break;
            case R.id.personcreate_1:
                if (list.get(0)){
                    list.set(0,false);
                    select_1.setBackgroundResource(R.drawable.select_right);
                }else {
                    list.set(0,true);
                    select_1.setBackgroundResource(R.drawable.select_right_tian);
                }
                break;

            case R.id.personcreate_2:
                if (list.get(1)){
                    list.set(1,false);
                    select_2.setBackgroundResource(R.drawable.select_right);
                }else {
                    list.set(1,true);
                    select_2.setBackgroundResource(R.drawable.select_right_tian);
                }
                break;

            case R.id.personcreate_3:
                if (list.get(2)){
                    list.set(2,false);
                    select_3.setBackgroundResource(R.drawable.select_right);
                }else {
                    list.set(2,true);
                    select_3.setBackgroundResource(R.drawable.select_right_tian);
                }
                break;

            case R.id.personcreate_4:
                if (hard!=1){
                    hard=1;
                    select_4.setBackgroundResource(R.drawable.select_right_tian);
                    select_5.setBackgroundResource(R.drawable.select_right);
                    select_6.setBackgroundResource(R.drawable.select_right);
                }
                break;

            case R.id.personcreate_5:
                if (hard!=2){
                    hard=2;
                    select_4.setBackgroundResource(R.drawable.select_right);
                    select_5.setBackgroundResource(R.drawable.select_right_tian);
                    select_6.setBackgroundResource(R.drawable.select_right);
                }
                break;

            case R.id.personcreate_6:
                if (hard!=3){
                    hard=3;
                    select_4.setBackgroundResource(R.drawable.select_right);
                    select_5.setBackgroundResource(R.drawable.select_right);
                    select_6.setBackgroundResource(R.drawable.select_right_tian);
                }
                break;

        }
    }
    private void choosesex(){
        switch (sex){
            case 1:
                sex=2;
                f_sex=1;
                person_sex.setImageResource(R.drawable.sex_man);
                break;
            case 2:
                sex=3;
                f_sex=2;
                person_sex.setImageResource(R.drawable.sex_women);
                break;
            case 3:
                sex=1;
                f_sex=3;
                person_sex.setImageResource(R.drawable.sex_mid);
                break;
        }
    }
    //初始化并弹出对话框方法
    private void showtouxiangDialog(){
        final View dialogview = LayoutInflater.from(this).inflate(R.layout.touxiang_dialog,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogview).create();
        dialog.setCancelable(false);
        RadioGroup rg=(RadioGroup)dialogview.findViewById(R.id.rg_tx);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)dialogview.findViewById(checkedId);
                String s=radioButton.getText().toString();
                int i=Integer.valueOf(s);
                choosetouxiang(i);
                toast(s);
                dialog.cancel();
            }
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = 400;
        window.setAttributes(params);
        window.setGravity(Gravity.TOP);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha0);
        dialog.show();
    }
    private void choosetouxiang(int i){
        switch (i){
            case 1:
                person_touxiang.setImageResource(R.drawable.touxiang1);
                person.setPerson_img(R.drawable.touxiang1);
                break;
            case 2:
                person_touxiang.setImageResource(R.drawable.touxiang2);
                person.setPerson_img(R.drawable.touxiang2);
                break;
            case 3:
                person_touxiang.setImageResource(R.drawable.touxiang3);
                person.setPerson_img(R.drawable.touxiang3);
                break;
            case 4:
                person_touxiang.setImageResource(R.drawable.touxiang1);
                person.setPerson_img(R.drawable.touxiang1);
                break;
            case 5:
                person_touxiang.setImageResource(R.drawable.touxiang2);
                person.setPerson_img(R.drawable.touxiang2);
                break;
            case 6:
                person_touxiang.setImageResource(R.drawable.touxiang3);
                person.setPerson_img(R.drawable.touxiang3);
                break;
            case 7:
                person_touxiang.setImageResource(R.drawable.touxiang1);
                person.setPerson_img(R.drawable.touxiang1);
                break;
            case 8:
                person_touxiang.setImageResource(R.drawable.touxiang2);
                person.setPerson_img(R.drawable.touxiang2);
                break;
            case 9:
                person_touxiang.setImageResource(R.drawable.touxiang3);
                person.setPerson_img(R.drawable.touxiang3);
                break;
        }
    }
}
