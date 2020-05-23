package ftd.txf.com.gamelife.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.base.BaseActivity;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.record.presenter.RecordPresenter;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class ChooseIMGActivity extends BaseActivity implements RecordContract.View {

    @BindView(R.id.yimaojian_back)
    ImageView YimaojianBack;
    @BindView(R.id.yimaojian_gold)
    TextView YimaojianGold;
    @BindView(R.id.yimaojian_showperson)
    GifImageView PersonShow;
    @BindView(R.id.yimaojian_left_person)
    ImageView Leftperson;
    @BindView(R.id.yimaojian_choose_person)
    ImageView Choseperson;
    @BindView(R.id.yimaojian_name_person)
    TextView Nameperson;
    @BindView(R.id.yimaojian_right_person)
    ImageView Rightperson;
    @BindView(R.id.yimaojian_congwushow)
    GifImageView CongwuShow;
    @BindView(R.id.yimaojian_left_congwu)
    ImageView Leftcongwu;
    @BindView(R.id.yimaojian_choose_congwu)
    ImageView Chosecongwu;
    @BindView(R.id.yimaojian_name_congwu)
    TextView Namecongwu;
    @BindView(R.id.yimaojian_right_congwu)
    ImageView Rightcongwu;
    @BindView(R.id.yimaojian_button)
    Button YimaoButton;

    //标志位
    private int person_show=1;
    private int congwu_show=1;

    private GifDrawable persongif;
    private GifDrawable congwugif;
    //presenter类
    private RecordContract.Presenter presenter;
    private Person yperson;
    @Override
    public int intiLayout() {
        return R.layout.activity_choose_img;
    }

    @Override
    public void setWindow() {
        setShow(false,true);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        presenter=new RecordPresenter(this);
        DealData();
        YimaojianBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoMain();
            }
        });
        persongif=(GifDrawable)PersonShow.getDrawable();
        persongif.setLoopCount(0);
        congwugif=(GifDrawable)CongwuShow.getDrawable();
        congwugif.setLoopCount(0);
        Rightperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (person_show<2){
                    person_show++;
                    changePerson();
                }
            }
        });
        Leftperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (person_show>0){
                    person_show--;
                    changePerson();
                }
            }
        });
        Rightcongwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (congwu_show<2){
                    congwu_show++;
                    changeCongwu();
                }
            }
        });
        Leftcongwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (congwu_show>0){
                    congwu_show--;
                    changeCongwu();
                }
            }
        });
    }
    private void changePerson(){
        switch (person_show){
            case 0:
                persongif.stop();
                PersonShow.setImageResource(R.drawable.personshow2);
                Choseperson.setImageResource(R.drawable.personchoose2);
                Nameperson.setText("超人宝宝");
                break;
            case 1:
                persongif.stop();
                PersonShow.setImageResource(R.drawable.personshow1);
                Choseperson.setImageResource(R.drawable.personchoose1);
                Nameperson.setText("初始宝宝");
                break;
            case 2:
                persongif.stop();
                PersonShow.setImageResource(R.drawable.personshow3);
                Choseperson.setImageResource(R.drawable.personchoose3);
                Nameperson.setText("木乃伊宝宝");
                break;
        }
    }
    private void changeCongwu(){
        switch (congwu_show){
            case 0:
                congwugif.stop();
                CongwuShow.setImageResource(R.drawable.congwu1);
                Chosecongwu.setImageResource(R.drawable.congwu1);
                Namecongwu.setText("哈欠橘猫");
                break;
            case 1:
                congwugif.stop();
                CongwuShow.setImageResource(R.drawable.congwu2);
                Chosecongwu.setImageResource(R.drawable.congwu2);
                Namecongwu.setText("憨憨二哈");
                break;
            case 2:
                congwugif.stop();
                CongwuShow.setImageResource(R.drawable.congwu3);
                Chosecongwu.setImageResource(R.drawable.congwu3);
                Namecongwu.setText("可爱柯基");
                break;
        }
    }
    @Override
    public void initData() {
        YimaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImg();
                presenter.savaPerson(yperson);
                toast("设定成功");
            }
        });
    }
    private void saveImg(){
        switch (person_show){
            case 0:
                yperson.setPersongif(R.drawable.personshow2);
                break;
            case 1:
                yperson.setPersongif(R.drawable.personshow1);
                break;
            case 2:
                yperson.setPersongif(R.drawable.personshow3);
                break;
        }
        switch (congwu_show){
            case 0:
                yperson.setCongwugif(R.drawable.congwu1);
                break;
            case 1:
                yperson.setCongwugif(R.drawable.congwu2);
                break;
            case 2:
                yperson.setCongwugif(R.drawable.congwu3);
                break;
        }
    }

    @Override
    public Context setMVPContext() {
        return ChooseIMGActivity.this;
    }

    @Override
    public void DealData() {
        presenter.setContext();
        yperson=presenter.getvPerson();
    }

    /**
     * 跳转主活动
     */
    private void GoMain(){
        Intent intent=new Intent();
        intent.putExtra("Fragment",4);
        intent.setClass(ChooseIMGActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
