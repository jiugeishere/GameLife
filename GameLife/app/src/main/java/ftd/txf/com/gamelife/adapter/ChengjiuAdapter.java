package ftd.txf.com.gamelife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.entity.Chengjiu;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;
import ftd.txf.com.gamelife.entity.Record;
import ftd.txf.com.gamelife.utils.DBUtils;
import ftd.txf.com.gamelife.utils.RoundProgressBar;

public class ChengjiuAdapter extends RecyclerView.Adapter <ChengjiuAdapter.ViewHolder>{
    private List<Chengjiu> chengjiuList;
    private Person mperson;
    private DBUtils mDBUtils;
    static class ViewHolder  extends RecyclerView.ViewHolder{

        View chengjiuView;
        private ImageView chengjiuImage;
        private TextView chengjiuname;
        private TextView chengjiutime;
        private TextView chengjiulimit;
        private TextView chengjiuway;
        private TextView chengjiuisget;
        private RoundProgressBar roundProgressBar;
        public ViewHolder(View view){
            super(view);
            chengjiuView = view;
            chengjiuImage=(ImageView)view.findViewById(R.id.chengjiu_img);
            chengjiuname=(TextView)view.findViewById(R.id.chengjiu_name);
            chengjiutime=(TextView)view.findViewById(R.id.chengjiu_createtime);
            chengjiulimit=(TextView)view.findViewById(R.id.chengjiu_limit);
            chengjiuway=(TextView)view.findViewById(R.id.chengjiu_way);
            chengjiuisget=(TextView)view.findViewById(R.id.chengjiu_isget);
            roundProgressBar=(RoundProgressBar)view.findViewById(R.id.chengjiu_roundProgressBar);
        }
    }
    public ChengjiuAdapter(List<Chengjiu> list){
        chengjiuList = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from (parent.getContext() )
                .inflate (R.layout.chengjiu_item,parent,false );
        final ViewHolder holder = new ViewHolder ( view );
        mDBUtils=new DBUtils(holder.chengjiuView.getContext());
        final Person person=mDBUtils.queryPersonById(1l);
        final Record record=mDBUtils.queryRecordById(2L);
        final PersonValue personValue=person.getPersonValue();
        //点击事件的监控和处理
        holder.chengjiuView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition ();
                Chengjiu chengjiu=chengjiuList.get(position);
                if (record.getMorebiaozhi()==2){
                    if (chengjiu.isChengjiu_done()){
                        personValue.setChenghao(chengjiu.getChengjiu_name());
                        Toast.makeText ( v.getContext (),"已装备"+
                                chengjiu.getChengjiu_name(),Toast.LENGTH_SHORT ).show ();
                        person.setPersonValue(personValue);
                        mDBUtils.updatePerson(person);
                    }else {
                        Toast.makeText ( v.getContext (),"此成就未解锁"
                                ,Toast.LENGTH_SHORT ).show ();
                    }
                }
                return false;
            }
        });
        return holder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        Chengjiu chengjiu=chengjiuList.get(position);
        holder.chengjiuImage.setImageResource(chengjiu.getChengjiu_photo());
        holder.chengjiuname.setText(chengjiu.getChengjiu_name());
        holder.chengjiutime.setText(chengjiu.getChengjiu_time());
        holder.chengjiuway.setText(chengjiu.getChengjiu_style());
        holder.chengjiulimit.setText(String.valueOf(chengjiu.getChengjiu_limit()));
        holder.roundProgressBar.setProgress(chengjiu.getChengjiu_jingdu());
        if (!chengjiu.isChengjiu_done()){
            holder.chengjiuisget.setText("");
        }else {
            holder.chengjiuisget.setText("已获得");
        }
    }
    public int getItemCount(){
        return chengjiuList.size ();
    }
}