package ftd.txf.com.gamelife.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.adapter.WorkAdapter;
import ftd.txf.com.gamelife.base.BaseFragment;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.fragment.MineFragment;
import ftd.txf.com.gamelife.utils.DBUtils;
import ftd.txf.com.gamelife.work.contract.WorkContract;
import ftd.txf.com.gamelife.work.presenter.WorkPresenter;

public class WorkFragment extends BaseFragment<WorkContract.Presenter> implements WorkContract.View,
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener{
    private final static String TAG = MineFragment.class.getSimpleName();


    private List<Work> lists;
    private List<Work> xtlists;
    private List<Work> workList2=new ArrayList<>();
    private DBUtils mDBUtils;
    private List<RecordOne> mRecordones;

    /*
     *布局绑定
     */
    @BindView(R.id.xitong_layout)
    LinearLayout Xitong_layout;
    @BindView(R.id.work_list)
    RecyclerView listView;
    @BindView(R.id.xitong_list)
    RecyclerView xtlistView;
//    @BindView(R.id.work_setting_more)
//    ImageView workset;
    @BindView(R.id.tv_year)
    TextView mTextYear;
    @BindView(R.id.tv_month_day)
    TextView mTextMonthDay;
    @BindView(R.id.tv_lunar)
    TextView mTextLunar;
    @BindView(R.id.tv_current_day)
    TextView mTextCurrentDay;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.rl_tool)
    RelativeLayout mRelativeTool;
    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;
    @BindView(R.id.fl_current)
    FrameLayout frameLayout;
//    @BindView(R.id.work_month_text)
//    TextView workmonth;
//    @BindView(R.id.work_year_text)
//    TextView workyear;
//    @BindView(R.id.calendarView)
//    CalendarView calendarView;
    @BindView(R.id.work_workdone_text)
    TextView Donetext;



    private int mYear;
    @Override
    public void initView() {
        LinearLayoutManager layoutManager2 = new LinearLayoutManager ( mContext );
        layoutManager2.setOrientation ( LinearLayoutManager.VERTICAL );
        xtlistView.setLayoutManager(layoutManager2);
        LinearLayoutManager layoutManager = new LinearLayoutManager ( mContext );
        layoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
        listView.setLayoutManager(layoutManager);
        updateView();
        getTime();
//        workset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.setClass(getActivity(),SettingActivity.class);
//                startActivity(intent);
//            }
//        });
        initD();
    }

    /**
     * 系统内容初始化
     * @return
     */
    private int GetXtWork(){
        int i=0;
        mDBUtils=new DBUtils(getActivity());
        mRecordones=mDBUtils.queryAllRecordOne();
        TimeGet timeGet=new TimeGet();
        String s=timeGet.getOldDate(0);
        String[] a=s.split("-");
        for (Work work:xtlists){
            for (RecordOne recordOne:mRecordones){
                String[] r=recordOne.getFinishtime().split(":");
                if (r[0].equals(a[0]+a[1]+a[2])&&work.getW_id()==-1){
                    if (recordOne.getWork_name().equals(work.getWork_name())){
                        work.setWork_name("该任务已完成");
                        work.setW_id(-2L);
                        i++;
                    }
                }
            }
            workList2.add(work);
        }
        return i;
    }
    @Override
    public WorkContract.Presenter onCreatePresenter() {
        presenter=new WorkPresenter(this);
        return presenter;
    }
    private void getTime(){

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));

    }
    private void initD(){
        int month,day,year,percent,daycount=0;
        String s;
        Map<String, Calendar> map = new HashMap<>();
        DBUtils wDBUtils=new DBUtils(getContext());
        List<RecordOne> mrecordOnes=wDBUtils.queryAllRecordOne();
        int count=0;
        for (RecordOne recordOne:mrecordOnes){
            s=recordOne.getFinishtime();
            day=Integer.valueOf(s.substring(6,8));
            if (daycount==day){
                if (count<=3){
                    count++;
                }else {
                    break;
                }
            }else {
                daycount=day;
                count=1;
            }
            year=Integer.valueOf(s.substring(0,4));
            month=Integer.valueOf(s.substring(4,6));
            percent=count*100/3;
            map.put(getSchemeCalendar(year, month, day, R.color.darkorange, String.valueOf(percent)).toString(),
                    getSchemeCalendar(year, month, day, R.color.darkorange, String.valueOf(percent)));

        }
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);

    }
    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }


    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextMonthDay.setText(String.valueOf(calendar.getMonth() + "月" + calendar.getDay() + "日"));
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }
    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }
    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    @Override
    public int setLayoutResourceID(){
        return R.layout.fragment_work;
    }

    @Override
    public void updateView(){
        lists=presenter.sendWorkdata(mContext);
        xtlists=presenter.sendXitongdata(4);
        String s=GetXtWork()+"/3";
        Donetext.setText(s);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
        boolean tiaozhang=sharedPreferences.getBoolean("tiaozhang",false);
        boolean showxitong=sharedPreferences.getBoolean("showxitong",false);        //是否隐藏系统
        xtlistView.setAdapter(new WorkAdapter(workList2,tiaozhang));
        if (showxitong){
            Xitong_layout.setVisibility(View.GONE);
        }
        listView.setAdapter(new WorkAdapter(lists,tiaozhang));
    }

    @Override
    public void saveWork(Work work){

    }
}


