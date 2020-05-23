package ftd.txf.com.gamelife.record.contract;

import android.content.Context;

import java.util.List;

import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.RecordOne;

public interface RecordContract {
    interface Model {
        void BindDBUtils(Context context);
        Record getRecord();
        void updateRecord(Record record);
        Person getmPerson();
        void updatePerson(Person person);
        void updatePersonValue(PersonValue personValue);
        List<RecordOne> getRecordones();
        void insetRecordone(RecordOne recordOne);
        void insertChengjiu(Chengjiu chengjiu);
        void updateChengjiu(Chengjiu chengjiu);
        List<Chengjiu> getMchengjius();
    }

    interface View {
        Context setMVPContext();
        void DealData();
    }

    interface Presenter {
        void saveRecord(Record record);
        int Averagetime();
        Record getRecord();
        List<Float> Get7Day(int i);
        void setContext();
        List<Integer> GetHour();
        List<Chengjiu> getChengjiu();

        void addChengjiu(Chengjiu chengjiu);

        List<RecordOne> getRecordones();

        void addRecordones(RecordOne recordOne);

        void setShuxing(String s);

        Person getvPerson();

        PersonValue getVpersonValue();
        void setTime(int time);
        void savaPerson(Person person);

        void set_recordmore(int i);
        int get_recordmore();
    }
}
