package ftd.txf.com.gamelife.work.contract;

import android.content.Context;

import java.util.List;

import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.utils.WorkDBUtils;

public interface WorkContract {
    interface Model {
        List<Work> getWorkdata( Context context);
        List<Work> getXiTongdata(int way);
        void saveWork(Work work);
    }

    interface View {
        void updateView();
        void saveWork(Work work);
    }

    interface Presenter {
        List<Work> sendWorkdata(Context context);
        List<Work> sendXitongdata(int i);
        void saveWork(Work work);
    }
}
