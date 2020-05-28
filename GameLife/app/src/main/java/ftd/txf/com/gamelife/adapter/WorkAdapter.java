package ftd.txf.com.gamelife.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.activity.GetDoneActivity;
import ftd.txf.com.gamelife.activity.TiaozhangActivity;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.entity.RecordOne;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.entity.Work;
import ftd.txf.com.gamelife.activity.TimerActivity;
import ftd.txf.com.gamelife.utils.DBUtils;
import ftd.txf.com.gamelife.utils.WorkDBUtils;

public class WorkAdapter extends RecyclerView.Adapter <WorkAdapter.ViewHolder>{
    private List<Work> workList;
    private boolean Tiaozhang;
    private WorkDBUtils mwordDBUtils;

    static class ViewHolder  extends RecyclerView.ViewHolder{
        View workView;
        private ImageView monsterImage;
        private TextView workName;
        private TextView monsterExp;
        private TextView planContent;
        private TextView monstername;
        private TextView planWay;
        private Button startbutton;
        private ImageView tiaozhangimg;
        public ViewHolder(View view){
            super(view);
            workView = view;
            monstername=(TextView)view.findViewById(R.id.text_monster_name);     //怪物名
            monsterImage = (ImageView) view.findViewById(R.id.monster_img);      //怪物图片
            workName = (TextView) view.findViewById(R.id.work_text);              //计划名
            monsterExp=(TextView)view.findViewById(R.id.monster_exp);             //经验
            planContent=(TextView)view.findViewById(R.id.plan_content);           //内容
            tiaozhangimg=(ImageView)view.findViewById(R.id.work_tiaozhang);      //挑战图片
            planWay=(TextView)view.findViewById(R.id.plan_way);
            startbutton=(Button)view.findViewById(R.id.start_button);
        }
    }
    public WorkAdapter(List<Work> list,boolean tiaozhang){
        workList = list;
        Tiaozhang=tiaozhang;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from (parent.getContext() )
                .inflate (R.layout.work_item,parent,false );
        final ViewHolder holder = new ViewHolder ( view );
        //点击事件的监控和处理
        mwordDBUtils=new WorkDBUtils(holder.workView.getContext());

        holder.workView.setOnClickListener ( new View.OnClickListener (){
            public void onClick(View v){
                int position = holder.getAdapterPosition ();
                Work work = workList.get (position);
                Toast.makeText ( v.getContext (),"you clicked view "+
                        work.getMonster_name()+position,Toast.LENGTH_SHORT ).show ();
            }
        } );
        holder.workView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition ();
                Work work = workList.get (position);
                mwordDBUtils.deleteWork(work);
                Toast.makeText ( v.getContext (),"删除成功 ",Toast.LENGTH_SHORT ).show ();
                return false;
            }
        });
        holder.startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition ();
                Work work = workList.get (position);
                if (work.getW_id()!=-2L){
                    Intent intent=new Intent();
                    intent.putExtra("Data_id",work.getW_id());
                    intent.putExtra("XT_postion",position);
                    if (work.getWork_ways().equals("不计时")){
                        intent.setClass(holder.workView.getContext(),GetDoneActivity.class);
                    }else if (work.getWork_ways().equals("倒计时")&&Tiaozhang){
                        intent.setClass(holder.workView.getContext(), TiaozhangActivity.class);
                    }else {
                        intent.setClass(holder.workView.getContext(), TimerActivity.class);
                    }
                    holder.workView.getContext().startActivity(intent);
                }else {
                    Toast.makeText ( v.getContext (),"已经完成喽! ",Toast.LENGTH_SHORT ).show ();
                }
            }
        });
        return holder;
    }



    public void onBindViewHolder(ViewHolder holder,int position){
        Work work = workList.get(position);
        if (work.getWork_ways().equals("不计时")){
            holder.startbutton.setText("打卡");
        }
        if (Tiaozhang&&work.getWork_ways().equals("倒计时")){
            holder.tiaozhangimg.setVisibility(View.VISIBLE);
        }
        holder.monsterImage.setImageResource(work.getMonster_img());
        holder.workName.setText(work.getWork_name());                                          //为文本视图设置文本内容
        holder.monstername.setText(work.getMonster_name());
        String exp="+"+ Integer.toString(work.getMonster_exp())+"exp";
        holder.planWay.setText(work.getWork_ways());
        holder.monsterExp.setText(exp);
        holder.planContent.setText(work.getContent());
    }
    public int getItemCount(){
        return workList.size ();
    }
}