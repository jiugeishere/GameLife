package ftd.txf.com.gamelife.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {
    /***封装toast对象**/
    private static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setWindow();
        //设置布局
        setContentView(intiLayout());
        //初始化控件

        initView();


        //设置数据
        initData();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();
    public abstract void setWindow();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    public void setShow(boolean isshowtitle,boolean isshowstate) {
        if(!isshowtitle){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if(!isshowstate){
            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN);
        }
    }


    /**
     * 显示toast
     */

    public void toast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

}
