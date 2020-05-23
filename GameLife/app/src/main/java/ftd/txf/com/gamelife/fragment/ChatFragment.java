package ftd.txf.com.gamelife.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.adapter.ChatAdapter;
import ftd.txf.com.gamelife.base.BaseFragment;

public class ChatFragment extends BaseFragment {

    private final static String TAG = ChatFragment.class.getSimpleName();


    @BindView(R.id.chat_recycler)
    RecyclerView chat_recyclerView;
    @BindView(R.id.chat_function)
    ImageView Function;
    @Override
    public int setLayoutResourceID(){
        return R.layout.fragment_chat;
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager ( mContext );
        layoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
        chat_recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void initData() {
        super.initData();
         List<Integer> list=new ArrayList<>();
         list.add(1);
         list.add(2);
         list.add(3);
         list.add(4);
         chat_recyclerView.setAdapter(new ChatAdapter(list));

    }
}