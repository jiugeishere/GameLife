package ftd.txf.com.gamelife.record;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.activity.RecordMoreActivity;
import ftd.txf.com.gamelife.adapter.ChatAdapter;
import ftd.txf.com.gamelife.adapter.ChengjiuAdapter;
import ftd.txf.com.gamelife.adapter.PaihanAdapter;
import ftd.txf.com.gamelife.base.BaseFragment;
import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.fragment.MineFragment;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.record.presenter.RecordPresenter;
import ftd.txf.com.gamelife.utils.RoundProgressBar;
import ftd.txf.com.gamelife.work.presenter.WorkPresenter;

public class RecordFragment extends BaseFragment implements RecordContract.View {

    private final static String TAG = MineFragment.class.getSimpleName();
    private TextView textView;
    @BindView(R.id.record_mubiao_piechart)
    PieChart mPieChart;
    @BindView(R.id.paihang_recycler)
    RecyclerView paihang_recyclerView;
    @BindView(R.id.chengjiu_recycler)
    RecyclerView chengjiu_recyclerview;
    @BindView(R.id.record_jiezhi)
    TextView Jiezhi;
    @BindView(R.id.record_averagetime)
    TextView AverageTime;
    @BindView(R.id.record_besttime)
    TextView BestTime;

    @BindView(R.id.record_morechengjiu)
    TextView Morechengjiu;
    @BindView(R.id.record_morepaihan)
    TextView Morepaihan;
    @BindView(R.id.record_moredata)
    TextView Moredata;

    //presenter类
    private RecordContract.Presenter presenter;
    private Record record;

    @Override
    public int setLayoutResourceID(){
        return R.layout.fragment_record;
    }
    @Override
    public void initView() {
        setPosWay();
        LinearLayoutManager layoutManager = new LinearLayoutManager ( mContext );
        layoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
        paihang_recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager ( mContext );
        layoutManager2.setOrientation ( LinearLayoutManager.VERTICAL );
        chengjiu_recyclerview.setLayoutManager(layoutManager2);
        TimeGet timeGet=new TimeGet();
        Jiezhi.setText("截至"+timeGet.getDayFormat());
        presenter=new RecordPresenter(this);
    }

    @Override
    public void initData() {
        super.initData();
        DealData();
        initPaihang();
        initChengjiu();
        initPieData();
        initPieChart();
        BestTime.setText(String.valueOf(record.getMaxWorktime()));
        AverageTime.setText(String.valueOf(presenter.Averagetime()));
        moreGo();
    }

    @Override
    public Context setMVPContext(){
        return getActivity();
    }

    @Override
    public void DealData(){
        presenter.setContext();
        record=presenter.getRecord();
    }

    /**
     * 查看更多跳转
     */
    private void moreGo(){

        Moredata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.set_recordmore(1);
                Intent intent=new Intent();
                intent.setClass(getContext(),RecordMoreActivity.class);
                startActivity(intent);
            }
        });

        Morechengjiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.set_recordmore(2);
                Intent intent=new Intent();
                intent.setClass(getActivity(),RecordMoreActivity.class);
                startActivity(intent);
            }
        });

        Morepaihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.set_recordmore(3);
                Intent intent=new Intent();
                intent.setClass(getActivity(),RecordMoreActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initChengjiu(){
        List<Chengjiu> chengjiuLists=presenter.getChengjiu();
        List<Chengjiu> chengjiuLists2=new ArrayList<Chengjiu>();
        chengjiuLists2.add(chengjiuLists.get(0));
        chengjiuLists2.add(chengjiuLists.get(4));
        chengjiuLists2.add(chengjiuLists.get(8));
        chengjiu_recyclerview.setAdapter(new ChengjiuAdapter(chengjiuLists2));
    }
    private void initPaihang(){
        List<Person> personLists=new ArrayList<Person>();
        personLists.add(new Person(null,"玖哥",R.drawable.touxiang1,20203015,123,"学习",13,2,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"光头党",R.drawable.touxiang2,20203015,123,"学习",23,1,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"3",R.drawable.touxiang3,20203015,123,"学习",33,3,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        List<PersonValue> personValues=new ArrayList<PersonValue>();
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        paihang_recyclerView.setAdapter(new PaihanAdapter(personLists,personValues)); paihang_recyclerView.setAdapter(new PaihanAdapter(personLists,personValues));
    }
    private void setPosWay() {

  //      progesssValue.setText(new StringBuffer().append(progesss.getProgress()).append("%")); 获取进度条百分比

    }

    /*
     *Piechart数据导入
     */
    private void initPieData() {
        ArrayList<Integer> all_colors=new ArrayList<Integer>(Arrays.asList(Color.parseColor("#FFD700")
                ,Color.parseColor("#228B22")
                ,Color.parseColor("#DC143C")));
        List<PieEntry> yVals = new ArrayList<>();
        yVals.add(new PieEntry(record.getStudy_times(), "学习"));
        yVals.add(new PieEntry(record.getExcise_times(), "锻炼"));
        yVals.add(new PieEntry(record.getExtend_times(), "拓展"));


        PieDataSet pieDataSet = new PieDataSet(yVals, "");
        PieData pieData = new PieData(pieDataSet);

      //pieData.setValueTextColor(R.color.alpha0);
        pieDataSet.setSliceSpace(2f);           //设置饼状Item之间的间隙
        pieDataSet.setSelectionShift(4f);      //设置饼状Item被选中时变化的距离
        pieDataSet.setColors(all_colors);           //为DataSet中的数据匹配上颜色集(饼图Item颜色)
        //最终数据 PieData

        pieData.setDrawValues(false);            //设置是否显示数据实体(百分比，true:以下属性才有意义)

        mPieChart.setData(pieData);
    }
    /*
     *Piechart样式设计
     */
    private void initPieChart(){
        mPieChart.setBackgroundColor(Color.parseColor("#00000000"));
        Description description=new Description();
        description.setEnabled(false);
        mPieChart.setDescription(description);
        mPieChart.setEntryLabelColor(Color.parseColor("#00000000"));
        mPieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad);
        mPieChart.setEntryLabelTextSize(10);
        mPieChart.setExtraTopOffset(3);
        Legend l = mPieChart.getLegend();
        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setForm(Legend.LegendForm.CIRCLE); //设置图例的形状
        l.setFormSize(10);                      //设置图例的大小
        l.setFormToTextSpace(10f);              //设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
        l.setXEntrySpace(10f);                  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(8f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);                      //设置比例块Y轴偏移量
        l.setTextSize(14f);                      //设置图例标签文本的大小

        mPieChart.setDrawCenterText(true);               //是否绘制PieChart内部中心文本（true：下面属性才有意义）
//        mPieChart.setCenterText(" ");                 //设置PieChart内部圆文字的内容
        mPieChart.setCenterTextSize(15f);                //设置PieChart内部圆文字的大小
        mPieChart.setCenterTextColor(Color.parseColor("#036da9"));         //设置PieChart内部圆文字的颜色
        if (record.getStudy_times()==0&&record.getExcise_times()==0&&record.getExtend_times()==0){
            mPieChart.setCenterText("暂时没有数据");
        }else {
            mPieChart.setCenterText("点击显示数据");
        }
        /*
         *Piechart点击事件监听
         */
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry = (PieEntry) e;
                switch (pieEntry.getLabel()){
                    case "学习":
                        mPieChart.setCenterTextColor(Color.parseColor("#FFD700"));
                        break;
                    case "锻炼":
                        mPieChart.setCenterTextColor(Color.parseColor("#228B22"));
                        break;
                    case "拓展":
                        mPieChart.setCenterTextColor(Color.parseColor("#DC143C"));
                        break;
                }
                Double i=Double.valueOf(record.getStudy_times()+record.getExtend_times()+record.getExcise_times());
                if (i!=0){
                    i=Math.floor(pieEntry.getValue()*100/i);
                    mPieChart.setCenterText(i+"%");
                }
            }
            @Override
            public void onNothingSelected() {

            }
        });
    }

}