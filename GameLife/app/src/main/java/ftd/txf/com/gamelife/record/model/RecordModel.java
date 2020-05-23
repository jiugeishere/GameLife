package ftd.txf.com.gamelife.record.model;

import android.content.Context;

import java.util.List;

import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.record.contract.RecordContract;
import ftd.txf.com.gamelife.utils.DBUtils;

public class RecordModel implements RecordContract.Model {
    private DBUtils mDButils;
    private Record mRecord;
    private Person mPerson;
    private List<RecordOne> mrecordOnes;
    private List<Chengjiu> mchengjius;
    @Override
    public void BindDBUtils(Context context){
        mDButils=new DBUtils(context);
        mRecord=mDButils.queryRecordById(2L);
        mPerson=mDButils.queryPersonById(1L);
        mrecordOnes=mDButils.queryAllRecordOne();
        mchengjius=mDButils.queryAllChengjiu();
    }
    @Override
    public List<RecordOne> getRecordones(){
        return mrecordOnes;
    }
    @Override
    public void insetRecordone(RecordOne recordOne){
        mrecordOnes.add(recordOne);
        mDButils.insertRecordOne(recordOne);
    }
    @Override
    public void insertChengjiu(Chengjiu chengjiu){
        mchengjius.add(chengjiu);
        mDButils.insertChengjiu(chengjiu);
    }
    @Override
    public void updateChengjiu(Chengjiu chengjiu){
        mDButils.updateChengjiu(chengjiu);
    }
    @Override
    public List<Chengjiu> getMchengjius(){
        return mchengjius;
    }
    @Override
    public Record getRecord(){
        return mRecord;
    }

    @Override
    public void updateRecord(Record record){
        this.mRecord=record;
        mDButils.updateRecord(mRecord);
    }

   @Override
    public Person getmPerson(){
        return mPerson;
    }
   @Override
    public void updatePerson(Person person){
        this.mPerson=person;
        mDButils.updatePerson(person);
    }
    @Override
    public void updatePersonValue(PersonValue personValue){
        mDButils.updatePersonValue(personValue);
    }
}
