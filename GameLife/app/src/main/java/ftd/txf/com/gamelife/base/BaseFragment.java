package ftd.txf.com.gamelife.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P> extends Fragment {

    public P presenter;
    protected Context mContext;
    protected View mContentView;
    /**
     * 绑定
     */
    private Unbinder unbinder;


    /**
     * 当该类被系统创建的时候回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == mContentView) {
            mContentView = inflater.inflate(setLayoutResourceID(), container, false);
        }
        unbinder = ButterKnife.bind(this, mContentView);
        return mContentView;
    }

    //抽象类，由孩子实现，实现不同的效果
    public void initView(){

    }
    /**
     * 布局文件(必须重写)
     */
    protected abstract int setLayoutResourceID();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 创建presenter
        presenter = onCreatePresenter();
        initView();
        initData();
    }
    public P onCreatePresenter() {
        return null;
    }
    /**
     * 当子类需要联网请求数据的时候，可以重写该方法，该方法中联网请求
     */
    public void initData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    /**
     * Toast简写
     */
    public void toast(String str){
        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
    }
}