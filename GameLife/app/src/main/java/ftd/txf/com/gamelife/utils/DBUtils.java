package ftd.txf.com.gamelife.utils;

import android.content.Context;
import android.util.Log;

import java.util.List;

import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.Work;

public class DBUtils {
    private static final String TAG = DBUtils.class.getSimpleName();
    private DaoManager mManager;

    public DBUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }
    /**
     * 完成Person记录的插入，如果表未创建，先创建表
     *
     * @param person
     * @return
     */
    public boolean insertPerson(Person person) {
        boolean flag = false;

        flag = mManager.getDaoSession().getPersonDao().insertOrReplace(person) == -1 ? false : true;

        return flag;
    }
    /**
     * 修改一条Person数据
     */
    public boolean updatePerson(Person person){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(person);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 根据主键id查询Person
     */
    public Person queryPersonById(long key){
        return mManager.getDaoSession().load(Person.class, key);
    }
    /**
     * 完成PersonValue记录的插入，如果表未创建，先创建表
     *
     * @param personValue
     * @return
     */
    public boolean insertPersonValue(PersonValue personValue) {
        boolean flag = false;

        flag = mManager.getDaoSession().getPersonValueDao().insert(personValue) == -1 ? false : true;

        return flag;
    }
    /**
     * 修改一条PersonValue数据
     * @param personValue
     * @return
     */
    public boolean updatePersonValue(PersonValue personValue){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(personValue);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 根据主键id查询PersonValue
     * @param key
     * @return
     */
    public PersonValue queryPersonValueById(long key){
        return mManager.getDaoSession().load(PersonValue.class, key);
    }
    /**
     * 完成Record记录的插入，如果表未创建，先创建表
     *
     * @param record
     * @return
     */
    public boolean insertRecord(Record record) {
        boolean flag = false;
        flag = mManager.getDaoSession().getRecordDao().insertOrReplace(record) == -1 ? false : true;
        return flag;
    }

    /**
     * 修改一条Record数据
     * @param record
     * @return
     */
    public boolean updateRecord(Record record){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(record);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 根据主键id查询Record
     * @param key
     * @return
     */
    public Record queryRecordById(long key){
        return mManager.getDaoSession().load(Record.class, key);
    }

    /**
     * 完成多条chengjiu的插入
     *
     * @return
     */
    public boolean insertMultChengjiu(final List<Chengjiu> chengjius) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Chengjiu chengjiu : chengjius) {
                        mManager.getDaoSession().insertOrReplace(chengjiu);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 完成chengjiu记录的插入，如果表未创建，先创建Chengjiu表
     *
     * @param chengjiu
     * @return
     */
    public boolean insertChengjiu(Chengjiu chengjiu) {
        boolean flag = false;
        flag = mManager.getDaoSession().getChengjiuDao().insert(chengjiu) == -1 ? false : true;
        Log.i(TAG, "insert Chengjiu :" + flag + "-->" + chengjiu.toString());
        return flag;
    }
    /**
     * 修改一条数据
     * @param chengjiu
     * @return
     */
    public boolean updateChengjiu(Chengjiu chengjiu){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(chengjiu);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Chengjiu> queryAllChengjiu(){
        return mManager.getDaoSession().loadAll(Chengjiu.class);
    }


    /**
     * 完成recordOne记录的插入，如果表未创建，先创建RecordOne表
     *
     * @param recordOne
     * @return
     */
    public boolean insertRecordOne(RecordOne recordOne) {
        boolean flag = false;
        flag = mManager.getDaoSession().getRecordOneDao().insert(recordOne) == -1 ? false : true;
        Log.i(TAG, "insert RecordOne :" + flag + "-->" + recordOne.toString());
        return flag;
    }
    /**
     * 修改一条数据
     * @param recordOne
     * @return
     */
    public boolean updateRecordOne(RecordOne recordOne){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(recordOne);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<RecordOne> queryAllRecordOne(){
        return mManager.getDaoSession().loadAll(RecordOne.class);
    }


}
