package ftd.txf.com.gamelife.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.activity.ChooseIMGActivity;
import ftd.txf.com.gamelife.base.BaseFragment;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.record.presenter.RecordPresenter;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MineFragment extends BaseFragment implements RecordContract.View{

    @BindView(R.id.radarChart)
    RadarChart radarChart;
    @BindView(R.id.mine_img)
    ImageView MineImg;
    @BindView(R.id.mine_name)
    TextView MineName;
    @BindView(R.id.mine_rank)
    TextView MineRank;
    @BindView(R.id.mine_id)
    TextView MineBigId;
    @BindView(R.id.mine_sex)
    TextView MineSex;
    @BindView(R.id.mine_geyang)
    TextView MineGy;
    @BindView(R.id.mine_progress)
    ProgressBar MineProgress;

    @BindView(R.id.mine_worktimes)
    TextView MineWorktimes;
    @BindView(R.id.mine_day)
    TextView MineDay;
    @BindView(R.id.mine_chenghao)
    TextView MineChenghao;
    @BindView(R.id.mine_pianleixing)
    TextView MineLeixing;
    @BindView(R.id.mine_zhonghepingf)
    TextView Minepingfeng;
    @BindView(R.id.mine_study_point)
    TextView MineStudy;
    @BindView(R.id.mine_excise_point)
    TextView MineExcise;
    @BindView(R.id.mine_de_point)
    TextView MineExtend;
    @BindView(R.id.mine_mei_point)
    TextView MineMei;
    @BindView(R.id.mine_lao_point)
    TextView MineLao;
    @BindView(R.id.mine_person_gif)
    GifImageView PersonGif;
    @BindView(R.id.mine_congwu_gif)
    GifImageView CongWu;
    @BindView(R.id.mine_replaceimg)
    TextView MineRecplce;
    //presenter类
    private RecordContract.Presenter presenter;
    private Record mrecord;
    private Person mperson;
    private PersonValue mpersonValue;

    @Override
    public int setLayoutResourceID(){
        return R.layout.fragment_mine;
    }
    @Override
    public void initView() {
        presenter=new RecordPresenter(this);
        setMVPContext();
    }

    /**
     * 类型初始化
     */
    @Override
    public void initData() {
        super.initData();
        DealData();
        initRadarData();
        initRadarView();
        String s="";
        MineImg.setImageResource(mperson.getPerson_img());
        MineName.setText(mperson.getPerson_name());
        s="等级:"+mpersonValue.getPerson_rank();
        MineRank.setText(s);
        s="ID:"+mperson.getBig_ID();
        MineBigId.setText(s);
        switch (mperson.getPerson_sex()){
            case 1:
                MineSex.setText("性别:男");
                break;
            case 2:
                MineSex.setText("性别:女");
                break;
            case 3:
                MineSex.setText("性别:未知");
                break;
        }
        s="成就称号:"+mpersonValue.getChenghao();
        MineChenghao.setText(s);
        if (mrecord.getStudy_times()>mrecord.getExtend_times()&&mrecord.getStudy_times()>mrecord.getExcise_times()){
            s="偏向任务类型:学习型";
        }else if (mrecord.getExtend_times()>mrecord.getExcise_times()){
            s="偏向任务类型:拓展型";
        }else {
            s="偏向任务类型:锻炼型";
        }
        MineLeixing.setText(s);
        s="人生格言:"+mperson.getPerson_geyan();
        MineGy.setText(s);
        MineStudy.setText(String.valueOf(mpersonValue.getShuxing_zhi()));
        MineExcise.setText(String.valueOf(mpersonValue.getShuxing_ti()));
        MineExtend.setText(String.valueOf(mpersonValue.getShuxing_de()));
        MineMei.setText(String.valueOf(mpersonValue.getShuxing_mei()));
        MineLao.setText(String.valueOf(mpersonValue.getShuxing_lao()));
        Minepingfeng.setText(String.valueOf(mpersonValue.getFinally_value_pingfeng()));
        s="累计完成:"+mpersonValue.getWorktimes()+"个任务";
        MineWorktimes.setText(s);
        int i=(mpersonValue.getPerson_rank()*100)+50;
        i=mpersonValue.getAll_exp()*100/i;
        MineProgress.setProgress(i);
        TimeGet timeGet=new TimeGet();
        i=timeGet.getDayDistanceCount(mperson.getCreatetime(),timeGet.getOldDate(0));
        i++;
        s="加入游戏人生:第"+i+"天";
        MineDay.setText(s);
        PersonGif.setImageResource(mperson.getPersongif());
        CongWu.setImageResource(mperson.getCongwugif());
        GifDrawable persondrawable=(GifDrawable) PersonGif.getDrawable();
        final GifDrawable congwudrawable=(GifDrawable) CongWu.getDrawable();
        persondrawable.start();
        congwudrawable.setLoopCount(1);
        CongWu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!congwudrawable.isPlaying()){
                    congwudrawable.reset();
                }
            }
        });
        MineRecplce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),ChooseIMGActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRadarData(){
        List<RadarEntry> list = new ArrayList<>();
        list.add(new RadarEntry((float) mpersonValue.getShuxing_zhi()));            //智
        list.add(new RadarEntry((float) mpersonValue.getShuxing_ti()));            //体
        list.add(new RadarEntry((float) mpersonValue.getShuxing_mei()));            //美
        list.add(new RadarEntry((float) mpersonValue.getShuxing_lao()));            //劳
        list.add(new RadarEntry((float) mpersonValue.getShuxing_de()));             //德
        RadarDataSet set = new RadarDataSet(list, "属性比例图");

        //禁用标签
        //set.setDrawValues(false);
        //设置填充颜色
        set.setFillColor(Color.parseColor("#036da9"));
        //设置填充透明度
        set.setFillAlpha(40);
        //设置启用填充
        set.setDrawFilled(true);
        //设置点击之后标签是否显示圆形外围
        set.setDrawHighlightCircleEnabled(false);
        set.setColor(Color.parseColor("#036da9"));
        set.setDrawValues(false);
        RadarData radarData=new RadarData(set);

        radarChart.setData(radarData);
        radarChart.invalidate();

    }
    private void initRadarView(){
        //设置web线的颜色(即就是外面包着的那个颜色)
        radarChart.setWebColorInner(Color.RED);
        //设置中心线颜色(也就是竖着的线条)
        radarChart.setWebColor(Color.BLACK);
        radarChart.setWebAlpha(50);
        radarChart.setExtraTopOffset(5f);

        XAxis xAxis = radarChart.getXAxis();
        //设置x轴标签字体颜色
        xAxis.setLabelCount(5, true);
        xAxis.setLabelCount(0);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value==0){
                    return "智";
                }else  if (value==1){
                    return "体";
                }else  if (value==2){
                    return "美";
                }else  if (value==3){
                    return "劳";
                }else  if (value==4){
                    return "德";
                }else {
                    return null;
                }

            }
        });
        YAxis yAxis = radarChart.getYAxis();
        //设置y轴的标签个数
        yAxis.setLabelCount(5, true);
        //设置y轴从0f开始
        yAxis.setAxisMinimum(0f);
        /*启用绘制Y轴顶点标签，这个是最新添加的功能
         * */

        yAxis.setDrawLabels(false);

        yAxis.setDrawTopYLabelEntry(false);
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return null;
            }
        });
        //设置字体颜色
        Legend l=radarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        //启用线条，如果禁用，则无任何线条
        radarChart.setDrawWeb(true);
        //禁用图例和图表描述
        radarChart.getDescription().setEnabled(false);
    }

    @Override
    public Context setMVPContext(){
        return getActivity();
    }

    @Override
    public void DealData(){
        presenter.setContext();
        mrecord=presenter.getRecord();
        mperson=presenter.getvPerson();
        mpersonValue=mperson.getPersonValue();
    }
}