package ftd.txf.com.gamelife.work.presenter;

import android.content.Context;

import java.util.List;

import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.work.contract.WorkContract;
import ftd.txf.com.gamelife.work.model.WorkModel;

public class WorkPresenter implements WorkContract.Presenter {
    private WorkContract.Model model;
    private WorkContract.View view;
    private List<Work> lists;
    private List<Work> xitonglists;
    private Work presrnt_work;
    public WorkPresenter(WorkContract.View view){
        this.view=view;
        this.model=new WorkModel();
    }
    @Override
    public List<Work> sendWorkdata(Context context){
            lists=model.getWorkdata(context);
            return lists;
    }

    @Override
    public List<Work> sendXitongdata(int i){
        xitonglists=model.getXiTongdata(i);
        return xitonglists;
    }

    @Override
    public void saveWork(Work work){
        int time=work.getPlan_time();
        if (work.getWork_mubiao().equals("锻炼")){
            work.setMonster_exp(time*3);
            work.setMonster_gold(time*5);
            work.setMonster_shuxing("体");
        }else if (work.getWork_mubiao().equals("学习")){
            work.setMonster_exp(time*2);
            work.setMonster_gold(time*4);
            work.setMonster_shuxing("智");
        }else {
                work.setMonster_exp(time);
                work.setMonster_gold(time*2);
            work.setMonster_shuxing("德");
        }
        if (work.getWork_ways().equals("不计时")){
            work.setMonster_exp(120);
            work.setMonster_gold(30);
        }
            model.saveWork(work);
    }

}

