package ftd.txf.com.gamelife.record.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.record.model.RecordModel;

public class RecordPresenter implements RecordContract.Presenter {
    private RecordContract.Model model;
    private RecordContract.View view;
    private Record vrecord;
    private List<Chengjiu> vchengjius;
    private List<RecordOne> vrecordones;
    private Person vperson;
    private PersonValue vpersonValue;
    public RecordPresenter(RecordContract.View view){
        this.view=view;
        this.model=new RecordModel();
    }
    public void set_recordmore(int i){
        vrecord.setMorebiaozhi(i);
        model.updateRecord(vrecord);
    }
    public int get_recordmore(){
        return vrecord.getMorebiaozhi();
    }
    @Override
    public void saveRecord(Record record){
        this.vrecord=record;
        model.updateRecord(record);
    }

    @Override
    public Record getRecord(){
        vrecord=model.getRecord();
        return vrecord;
    }
    @Override
    public void setContext(){
        model.BindDBUtils(view.setMVPContext());
        this.vrecord=model.getRecord();
        vchengjius=model.getMchengjius();
        vrecordones=model.getRecordones();
        vperson=model.getmPerson();
        vpersonValue=vperson.getPersonValue();
    }
    @Override
    public List<Chengjiu> getChengjiu(){
        return vchengjius;
    }
    @Override
    public void addChengjiu(Chengjiu chengjiu){
        vchengjius.add(chengjiu);
    }
    @Override
    public List<RecordOne> getRecordones(){
        return vrecordones;
    }
    @Override
    public void addRecordones(RecordOne recordOne){
        vrecordones.add(recordOne);
        int e=vpersonValue.getAll_exp();
        e=e+recordOne.getExp();
        int g=vpersonValue.getAll_gold();
        g=g+recordOne.getGold();
        vpersonValue.setAll_exp(e);
        vpersonValue.setAll_gold(g);
        int r=vpersonValue.getPerson_rank();
        while (e>(r*100)+50){
            r++;
            vpersonValue.setPerson_rank(r);
        }
        int pf=r*40+vpersonValue.getShuxing_lao()+vpersonValue.getShuxing_de()+vpersonValue.getShuxing_mei()+vpersonValue.getShuxing_ti()+vpersonValue.getShuxing_zhi()+vpersonValue.getWorktimes();
        vpersonValue.setFinally_value_pingfeng(pf);
        vpersonValue.setWorktimes(vpersonValue.getWorktimes()+1);
        model.insetRecordone(recordOne);
        model.updateRecord(vrecord);
        model.updatePersonValue(vpersonValue);
        model.updatePerson(vperson);
    }

    @Override
    public void setShuxing(String s){
        int i=0;
        int r=0;
        switch (s){
            case "体":
                i=vpersonValue.getShuxing_ti();
                r=vrecord.getExcise_times();
                i++;
                r++;
                vpersonValue.setShuxing_ti(i);
                vrecord.setExcise_times(r);
                Chengjiupangding(i,"次锻炼");
                break;
            case "智":
                i=vpersonValue.getShuxing_zhi();
                r=vrecord.getStudy_times();
                r++;
                i++;
                vpersonValue.setShuxing_zhi(i);
                vrecord.setStudy_times(r);
                Chengjiupangding(i,"次学习");
                break;
            case "德":
                r=vrecord.getExtend_times();
                i=vpersonValue.getShuxing_de();
                r++;
                i++;
                vpersonValue.setShuxing_de(i);
                vrecord.setExtend_times(r);
                Chengjiupangding(i,"次拓展");
                break;
        }
    }
    private void Chengjiupangding(int i,String s){
        TimeGet timeGet=new TimeGet();
        for (Chengjiu chengjiu:vchengjius){
            if (i==chengjiu.getChengjiu_limit()&&chengjiu.getChengjiu_style().equals(s)&&!chengjiu.getChengjiu_done()){
                chengjiu.setChengjiu_photo(R.drawable.chengjiu_jiangbei);
                chengjiu.setChengjiu_done(true);
                chengjiu.setChengjiu_time(timeGet.getDayFormat());
                chengjiu.setChengjiu_jingdu(100);
                vpersonValue.setShuxing_mei(vpersonValue.getShuxing_mei()+10+chengjiu.getChengjiu_limit());
                Toast.makeText(view.setMVPContext(),"获得成就"+chengjiu.getChengjiu_name(),Toast.LENGTH_LONG).show();
            }else if (chengjiu.getChengjiu_style().equals(s)){
                int d=(i*100)/chengjiu.getChengjiu_limit();
                chengjiu.setChengjiu_jingdu(d);
            }
            model.updateChengjiu(chengjiu);
        }
    }
    public void setTime(int time){
        if (time>vrecord.getMaxWorktime()){
            vrecord.setMaxWorktime(time);
        }
        vrecord.setAlltime(vrecord.getAlltime()+time);
        if (time>15&&time<=60){
            vpersonValue.setShuxing_lao(vpersonValue.getShuxing_lao()+1);
        }else if (time>60){
            vpersonValue.setShuxing_lao(vpersonValue.getShuxing_lao()+2);
        }
        int lastday=vrecord.getLastday();
        TimeGet timeGet=new TimeGet();
        if (timeGet.getToDay()-lastday==1){
            vrecord.setDayonnoew(vrecord.getDayonnoew()+1);
            if (vrecord.getDayonnoew()>vrecord.getMaxOneDay()){
                vrecord.setMaxOneDay(vrecord.getDayonnoew());
                Chengjiupangding(vrecord.getMaxOneDay(),"天连续使用计时功能");
            }
        }
        vrecord.setLastday(timeGet.getToDay());
    }

    /**
     * 计算均值
     * @return
     */
    @Override
    public int Averagetime(){
        int i=0;
        int a=0;
        for (RecordOne recordOne:vrecordones){
           i++;
        }
        if (i!=0){
            a=vrecord.getAlltime()/i;
        }
        return a;

    }
    @Override
    public List<Float> Get7Day(int i){
        TimeGet timeGet=new TimeGet();
        List<Float> listworks=new ArrayList<>();
        List<Float> listhours=new ArrayList<>();
        for (int p=0;p<7;p++){
            String s=timeGet.getOldDate(p-6);
            String[] a=s.split("-");
            float dayworks=0;
            float dayhours=0;
            for (RecordOne recordOne:vrecordones){
                String[] r=recordOne.getFinishtime().split(":");
                if (r[0].equals(a[0]+a[1]+a[2])){
                    dayworks++;
                    dayhours=dayhours+recordOne.getTime();
                }
            }
            dayhours=dayhours/60;
            listhours.add(dayhours);
            listworks.add(dayworks);
        }
        if (i==1){
            return listworks;
        }else {
            return listhours;
        }
    }

    @Override
    public List<Integer> GetHour(){
        List<Integer> listexps=new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0));
            for (RecordOne recordOne:vrecordones){
                String[] r=recordOne.getFinishtime().split(":");
               int time=Integer.valueOf(r[1]);
               if (time>0&&time<=4){
                   listexps.set(1,recordOne.getExp()+listexps.get(1));
               }else if (time>4&&time<=8){
                   listexps.set(2,recordOne.getExp()+listexps.get(2));
               }else if (time>8&&time<=12){
                   listexps.set(3,recordOne.getExp()+listexps.get(3));
               }else if (time>12&&time<=16){
                   listexps.set(4,recordOne.getExp()+listexps.get(4));
               }else if (time>16&&time<=20){
                   listexps.set(5,recordOne.getExp()+listexps.get(5));
               }else if (time>20&&time<=24){
                   listexps.set(6,recordOne.getExp()+listexps.get(6));
               }
        }
            return listexps;
    }
    @Override
    public Person getvPerson(){
        return vperson;
    }
    @Override
    public PersonValue getVpersonValue(){
        return vpersonValue;
    }
    @Override
    public void savaPerson(Person person){
        model.updatePerson(person);
    }

}
