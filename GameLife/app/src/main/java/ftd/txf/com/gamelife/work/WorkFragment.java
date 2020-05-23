package ftd.txf.com.gamelife.work;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.activity.WorkMoreActivity;
import ftd.txf.com.gamelife.adapter.WorkAdapter;
import ftd.txf.com.gamelife.base.BaseFragment;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.fragment.MineFragment;
import ftd.txf.com.gamelife.utils.DBUtils;
import ftd.txf.com.gamelife.work.contract.WorkContract;
import ftd.txf.com.gamelife.work.presenter.WorkPresenter;

public class WorkFragment extends BaseFragment<WorkContract.Presenter> implements WorkContract.View{
    private final static String TAG = MineFragment.class.getSimpleName();


    private List<Work> lists;
    private List<Work> xtlists;
    private List<Work> workList2=new ArrayList<>();
    private DBUtils mDBUtils;
    private List<RecordOne> mRecordones;
    /*
     *布局绑定
     */
    @BindView(R.id.work_list)
    RecyclerView listView;
    @BindView(R.id.xitong_list)
    RecyclerView xtlistView;
    @BindView(R.id.work_setting_more)
    ImageView workset;
    @BindView(R.id.work_month_text)
    TextView workmonth;
    @BindView(R.id.work_year_text)
    TextView workyear;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.work_workdone_text)
    TextView Donetext;
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
        workset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),WorkMoreActivity.class);
                startActivity(intent);
            }
        });
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
        final TimeGet timeGet=new TimeGet();

        workmonth.setText(timeGet.getFormatMonth(timeGet.getMonth()));
        String s=String.valueOf(timeGet.getYear());
        workyear.setText(s);
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                workmonth.setText(timeGet.getFormatMonth(month));
                String s=String.valueOf(year);
                workyear.setText(s);
            }
        });
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
        xtlistView.setAdapter(new WorkAdapter(workList2));
        listView.setAdapter(new WorkAdapter(lists));
    }

    @Override
    public void saveWork(Work work){

    }
}


