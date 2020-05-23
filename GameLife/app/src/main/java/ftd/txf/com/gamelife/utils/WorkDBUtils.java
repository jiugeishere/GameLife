package ftd.txf.com.gamelife.utils;


import android.content.Context;
import android.util.Log;

import com.greendaodemo.gen.WorkDao;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import ftd.txf.com.gamelife.entity.Work;

/**
 * 完成对某一张数据表的具体操作，ORM操作
 * Created by Mr.sorrow on 2017/5/5.
 */

public class WorkDBUtils {
    private static final String TAG = WorkDBUtils.class.getSimpleName();
    private DaoManager mManager;

    public WorkDBUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成work记录的插入，如果表未创建，先创建Work表
     *
     * @param work
     * @return
     */
    public boolean insertWork(Work work) {
        boolean flag = false;

        flag = mManager.getDaoSession().getWorkDao().insert(work) == -1 ? false : true;
        Log.i(TAG, "insert Work :" + flag + "-->" + work.toString());

        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param workList
     * @return
     */
    public boolean insertMultWork(final List<Work> workList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Work work : workList) {
                        mManager.getDaoSession().insertOrReplace(work);
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
     * 修改一条数据
     * @param work
     * @return
     */
    public boolean updateWork(Work work){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(work);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param work
     * @return
     */
    public boolean deleteWork(Work work){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(work);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(Work.class);
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
    public List<Work> queryAllWork(){
        return mManager.getDaoSession().loadAll(Work.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public Work queryWorkById(long key){
        return mManager.getDaoSession().load(Work.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<Work> queryWorkByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(Work.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<Work> queryWorkByQueryBuilder(long id){
        QueryBuilder<Work> queryBuilder = mManager.getDaoSession().queryBuilder(Work.class);
        return queryBuilder.where(WorkDao.Properties.W_id.eq(id)).list();
    }
}

