package ftd.txf.com.gamelife.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.adapter.ChengjiuAdapter;
import ftd.txf.com.gamelife.adapter.PaihanAdapter;
import ftd.txf.com.gamelife.base.BaseActivity;
import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.record.presenter.RecordPresenter;
import ftd.txf.com.gamelife.utils.DayFormater;

public class RecordMoreActivity extends BaseActivity implements RecordContract.View {

    @BindView(R.id.record_barchart)
    BarChart mBarChart;
    @BindView(R.id.record_linechart)
    LineChart mLineChart;
    @BindView(R.id.record_piechart)
    PieChart mPieChart;
    @BindView(R.id.record_morescroll)
    ScrollView DataScroll;
    @BindView(R.id.recordmore_text1)
    TextView text1;
    @BindView(R.id.recordmore_text2)
    TextView text2;
    @BindView(R.id.recycler_more)
    RecyclerView Morerecycler;
    @BindView(R.id.recordmore_back)
    ImageView Back;
    private BarData mBarData;
    //presenter类
    private RecordContract.Presenter presenter;
    private Record record;
    @Override
    public int intiLayout() {
        return R.layout.activity_record_more;
    }

    @Override
    public void setWindow(){
        setShow(false,true);
    }
    /**
     * 绑定监听
     */
    @Override
    public void initView() {
        ButterKnife.bind(this);
        presenter=new RecordPresenter(this);
        DealData();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoMain();
            }
        });

    }
    @Override
    public void initData(){
        int id;
        id=presenter.get_recordmore();
        TimeGet timeGet=new TimeGet();
        if (id!=1){
            DataScroll.setVisibility(View.GONE);
            LinearLayoutManager layoutManager = new LinearLayoutManager (RecordMoreActivity.this);
            layoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
            Morerecycler.setLayoutManager(layoutManager);
        }else {
            text1.setText("统计详情");
            String s="截至"+timeGet.getDayFormat();
            text2.setText(s);
            initBarData();
            initBarChart();
            initPieChart();
            initPieData();
            initLineData();
        }
        if (id==2){
            text1.setText("成就获取");
            text2.setText("长按装备称号");
            initChengjiu();
        }
        if (id==3){
            text1.setText("好友排行");
            text2.setText(" ");
            initPaihang();
        }
    }
    /**
     * 跳转主活动
     */
    private void GoMain(){
        presenter.set_recordmore(0);
        Intent intent=new Intent();
        intent.putExtra("Fragment",2);
        intent.setClass(RecordMoreActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void initChengjiu(){
        List<Chengjiu> chengjiuLists=presenter.getChengjiu();
        Morerecycler.setAdapter(new ChengjiuAdapter(chengjiuLists));
    }
    private void initPaihang(){
        List<Person> personLists=new ArrayList<Person>();
        personLists.add(new Person(null,"玖",R.drawable.touxiang1,20203015,123,"学习",13,2,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"2",R.drawable.touxiang2,20203015,123,"学习",23,1,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"3",R.drawable.touxiang3,20203015,123,"学习",33,3,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"4",R.drawable.touxiang3,20203015,123,"学习",43,5,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"5",R.drawable.touxiang2,20203015,123,"学习",13,7,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"6",R.drawable.touxiang1,20203015,123,"学习",23,6,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"7",R.drawable.touxiang2,20203015,123,"学习",33,4,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"8",R.drawable.touxiang1,20203015,123,"学习",43,2,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"9",R.drawable.touxiang3,20203015,123,"学习",13,1,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        personLists.add(new Person(null,"10",R.drawable.touxiang1,20203015,123,"学习",23,3,"",R.drawable.personshow1,R.drawable.personshow2,4L));
        List<PersonValue> personValues=new ArrayList<PersonValue>();
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        personValues.add(new PersonValue(1L,0,0,0,0,0,0,1,0,200,1,0,1100,"无"));
        Morerecycler.setAdapter(new PaihanAdapter(personLists,personValues));
    }
    @Override
    public Context setMVPContext(){
        return RecordMoreActivity.this;
    }

    @Override
    public void DealData(){
        presenter.setContext();
        record=presenter.getRecord();
    }
    private void initBarData() {
        // y 数据
        ArrayList<BarEntry> yValues = new ArrayList<>();
        ArrayList<BarEntry> yValues2 = new ArrayList<>();
        List<Float> list1=presenter.Get7Day(1);
        List<Float> list2=presenter.Get7Day(2);
        for (int x = 1; x <=7; x++) {
            yValues.add(new BarEntry(x,list1.get(x-1)));
        }
        for (int x = 1; x <=7; x++) {
            yValues2.add(new BarEntry(x, list2.get(x-1)));

        }
        // y 轴数据集
        BarDataSet barDataSet = new BarDataSet(yValues, "任务数");
        BarDataSet barDataSet2 = new BarDataSet(yValues2, "时长/h");
        barDataSet.setColor(Color.parseColor("#FFBC7127"));
        barDataSet2.setColor(Color.parseColor("#FFF4AF0F"));
        barDataSet.setValueTextColor(Color.GRAY);
        barDataSet.setValueTextSize(10);
        barDataSet2.setValueTextColor(Color.GRAY);
        barDataSet2.setValueTextSize(10);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value,
                                            com.github.mikephil.charting.data.Entry entry,
                                            int dataSetIndex, ViewPortHandler viewPortHandler) {
                int n = (int) value;
                return n+"";
            }
        });
        mBarData = new BarData(barDataSet,barDataSet2);

    }

    private void initBarChart() {
        TimeGet timeGet=new TimeGet();
        // 设置 条形图 简介
        Description description = new Description();
        mBarChart.setBackgroundColor(Color.WHITE);
        // 可以自定义 位置
        // 也可以 根据 X 轴，Y 轴进行偏移量设置
        mBarChart.setNoDataText("暂时没有数据");
        description.setYOffset(-32f);
        description.setXOffset(15f);
        description.setTextColor(Color.GRAY);
        description.setText("今天是"+timeGet.getDay()+"号");
        description.setTextSize(10f);
        mBarChart.setDescription(description);
        //设置 是否可以拖放

        mBarChart.setDragEnabled(false);
        mBarChart.setScaleEnabled(false);
        // 获取 x 轴
        XAxis xAxis = mBarChart.getXAxis();
        // 设置 x 轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 取消 垂直 网格线
        xAxis.setDrawGridLines(false);
        // 设置 x 轴 坐标字体大小
        xAxis.setTextSize(10f);
        // 设置 x 坐标轴 颜色
        xAxis.setAxisLineColor(Color.parseColor("#DC9E9D9D"));
        // 设置 x 坐标轴 宽度
        xAxis.setAxisLineWidth(1f);
        // 设置 x轴 的刻度数量
        xAxis.setLabelCount(7);
        //设置 x轴 的最大值
        //xAxis.setAxisMaximum(31);
        //设置 x轴 的最小值
        xAxis.setAxisMinimum(0);
        xAxis.setCenterAxisLabels(true);

//        //Day格式化
       DayFormater dayFormater=new DayFormater();
//        //初始日期获取
        dayFormater.setValue(1);
        xAxis.setValueFormatter(dayFormater);
        // 获取 右边 y 轴
        YAxis mRAxis = mBarChart.getAxisRight();
        // 隐藏 右边 Y 轴
        mRAxis.setEnabled(false);
        // 获取 左边 Y轴
        YAxis mLAxis = mBarChart.getAxisLeft();
        mLAxis.setEnabled(true);
        mLAxis.setGridColor(Color.parseColor("#58808080"));
        mLAxis.setAxisLineColor(Color.parseColor("#DC9E9D9D"));
        mLAxis.setAxisLineWidth(1f);
        // 取消 左边 Y轴 坐标线
       // mLAxis.setDrawAxisLine(false);
        // 取消 横向 网格线
        //mLAxis.setDrawGridLines(false);
        // 设置 Y轴 的刻度数量
       // mLAxis.setLabelCount(10);
        mLAxis.setAxisMinimum(0);
        Legend l=mBarChart.getLegend();
        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setForm(Legend.LegendForm.CIRCLE); //设置图例的形状
        l.setFormSize(10);                      //设置图例的大小
        l.setFormToTextSpace(4f);              //设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setXEntrySpace(5f);                  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(8f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(10f);                      //设置比例块Y轴偏移量
        l.setTextSize(10f);                      //设置图例标签文本的大小
        l.setTextColor(Color.GRAY);

        //设置Y轴最小值
        mBarData.setBarWidth(0.4f);
        mBarChart.setData(mBarData);
        mBarChart.groupBars(0f,0.21f,0f);
        mBarChart.animateY(1000);
    }

    private void initPieData() {
        ArrayList<Integer> all_colors=new ArrayList<Integer>();
        all_colors.add(Color.parseColor("#6785f2"));
        all_colors.add(Color.parseColor("#675cf2"));
        all_colors.add(Color.parseColor("#496cef"));
        all_colors.add(Color.parseColor("#aa63fa"));
        all_colors.add(Color.parseColor("#f5a658"));

        List<PieEntry> yVals = new ArrayList<>();
        yVals.add(new PieEntry(record.getMaxOneDay(),"最大坚持天数"));
        yVals.add(new PieEntry(record.getAlltime()/600,"累积时间/10h"));
        yVals.add(new PieEntry(record.getExtend_times()/10,"拓展累计次数/10次"));
        yVals.add(new PieEntry(record.getStudy_times()/10,"学习累计次数/10次"));
        yVals.add(new PieEntry(record.getExcise_times()/10,"锻炼累计次数/10次"));


        PieDataSet dataset = new PieDataSet(yVals, "");
        dataset.setColors(all_colors);
//显示为圆环
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleRadius(75f);//设置中间洞的大小

        //是否在图上显示数值
        dataset.setDrawValues(true);
//        文字的大小
        dataset.setValueTextSize(10);
//        文字的颜色
        dataset.setValueTextColor(Color.GRAY);
//        文字的样式
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

//      当值位置为外边线时，表示线的前半段长度。
        dataset.setValueLinePart1Length(0.4f);
//      当值位置为外边线时，表示线的后半段长度。
        dataset.setValueLinePart2Length(0.4f);
//      当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
        dataset.setValueLinePart1OffsetPercentage(80f);
        // 当值位置为外边线时，表示线的颜色。
        dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
//        设置Y值的位置是在圆内还是圆外
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        设置Y轴描述线和填充区域的颜色一致

//        设置每条之前的间隙
        dataset.setSliceSpace(0);

        //设置饼状Item被选中时变化的距离
        dataset.setSelectionShift(3f);
        //填充数据
        PieData pieData = new PieData(dataset);
//        显示视图
        mPieChart.setData(pieData);
    }
    private void initPieChart(){
        Description description = new Description();
        description.setText("");
        mPieChart.setDescription(description);
        //  是否显示中间的洞
        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setHoleRadius(40f);//设置中间洞的大小
        // 半透明圈
        mPieChart.setTransparentCircleRadius(30f);
        mPieChart.setTransparentCircleColor(Color.WHITE); //设置半透明圆圈的颜色
        mPieChart.setTransparentCircleAlpha(125); //设置半透明圆圈的透明度

        //饼状图中间可以添加文字
        mPieChart.setDrawCenterText(false);
        mPieChart.setCenterText("民族"); //设置中间文字
        mPieChart.setCenterTextColor(Color.parseColor("#a1a1a1")); //中间问题的颜色
        mPieChart.setCenterTextSizePixels(36);  //中间文字的大小px
        mPieChart.setCenterTextRadiusPercent(1f);
        mPieChart.setCenterTextTypeface(Typeface.DEFAULT); //中间文字的样式
        mPieChart.setCenterTextOffset(0, 0); //中间文字的偏移量


        mPieChart.setRotationAngle(0);// 初始旋转角度
        mPieChart.setRotationEnabled(true);// 可以手动旋转

        mPieChart.getDescription().setEnabled(false); //取消右下角描述

        //是否显示每个部分的文字描述
        mPieChart.setDrawEntryLabels(false);
        mPieChart.setEntryLabelColor(Color.RED); //描述文字的颜色
        mPieChart.setEntryLabelTextSize(14);//描述文字的大小
        mPieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD); //描述文字的样式

        //图相对于上下左右的偏移
        mPieChart.setExtraOffsets(20, 8, 75, 8);
        //图标的背景色
        mPieChart.setBackgroundColor(Color.TRANSPARENT);
//        设置pieChart图表转动阻力摩擦系数[0,1]
        mPieChart.setDragDecelerationFrictionCoef(0.75f);

        //获取图例
        Legend legend = mPieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);  //设置图例水平显示
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //顶部
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //右对其

        legend.setXEntrySpace(7f);//x轴的间距
        legend.setYEntrySpace(10f); //y轴的间距
        legend.setYOffset(10f);  //图例的y偏移量
        legend.setXOffset(10f);  //图例x的偏移量
        legend.setTextColor(Color.parseColor("#a1a1a1")); //图例文字的颜色
        legend.setTextSize(13);  //图例文字的大小


    }
    private void initLineData() {
        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        List<Integer> list=presenter.GetHour();
        for (int i = 0; i <= 5; i++){
            entries.add(new Entry(i, list.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "累积经验值"); // add entries to datase
        dataSet.setColor(Color.parseColor("#036da9"));//线条颜色
        dataSet.setCircleColor(Color.parseColor("#036da9"));//圆点颜色
        dataSet.setLineWidth(2f);//线条宽度
        dataSet.setValueTextColor(Color.GRAY);//设置值文本颜色
        dataSet.setValueTextSize(10);
        dataSet.setDrawCircles(true);
        dataSet.setDrawCircleHole(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //3.chart设置数据
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // refresh
        mLineChart.setDrawBorders(false);
        Description description=new Description();
        description.setText("");
        mLineChart.setDescription(description);
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        mLineChart.setExtraLeftOffset(1f);
        xAxis.setGranularity(0.4f);
        //设置滑动
        // 取消 垂直 网格线
        xAxis.setDrawGridLines(false);
        // 设置 x 轴 坐标字体大小
        xAxis.setTextSize(10f);
        // 设置 x 坐标轴 颜色
        xAxis.setAxisLineColor(Color.parseColor("#DC9E9D9D"));
        // 设置 x 坐标轴 宽度
        xAxis.setAxisLineWidth(1f);
        // 设置 x轴 的刻度数量
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setLabelCount(6,false);
        xAxis.setAxisMaximum(6f);
        xAxis.setAxisMinimum(0);
        xAxis.setDrawGridLines(false);
//        //Day格式化
        DayFormater dayFormater=new DayFormater();
//        //初始日期获取
        dayFormater.setValue(2);
        xAxis.setValueFormatter(dayFormater);
        // 获取 右边 y 轴
        YAxis mRAxis = mLineChart.getAxisRight();
        // 隐藏 右边 Y 轴
        mRAxis.setEnabled(false);
        // 获取 左边 Y轴
        YAxis mLAxis = mLineChart.getAxisLeft();
        mLAxis.setEnabled(true);
        mLAxis.setGridColor(Color.parseColor("#58808080"));
        mLAxis.setAxisLineColor(Color.parseColor("#DC9E9D9D"));
        mLAxis.setAxisLineWidth(1f);

        Legend l=mLineChart.getLegend();
        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setForm(Legend.LegendForm.CIRCLE); //设置图例的形状
        l.setFormSize(10);                      //设置图例的大小
        l.setFormToTextSpace(4f);              //设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setXEntrySpace(5f);                  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(8f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(10f);                      //设置比例块Y轴偏移量
        l.setTextSize(10f);                      //设置图例标签文本的大小
        l.setTextColor(Color.GRAY);
        mLineChart.setDragEnabled(false);
        mLineChart.setScaleEnabled(false);

        mLineChart.animateY(2000);//动画效果，MPAndroidChart中还有很多动画效果可以挖掘
    }
}
