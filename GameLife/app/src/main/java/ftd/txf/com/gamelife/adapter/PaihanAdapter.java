package ftd.txf.com.gamelife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.entity.Person;
import ftd.txf.com.gamelife.entity.PersonValue;

public class PaihanAdapter extends RecyclerView.Adapter <PaihanAdapter.ViewHolder>{
    private List<Person> personList;
    private List<PersonValue> personValueList;
    static class ViewHolder  extends RecyclerView.ViewHolder{
        View paihangView;
        private ImageView hunmanImage;
        private TextView paihang_number;
        private TextView paihang_haoyou;
        private TextView paihang_name;
        private TextView paihang_allworld;
        private TextView paihang_pingfeng;
        public ViewHolder(View view){
            super(view);
            paihangView = view;
            paihang_pingfeng=(TextView)view.findViewById(R.id.paihang_pingfeng_text);                 //评分
            hunmanImage = (ImageView) view.findViewById(R.id.paihang_manimage);                        //图片
            paihang_allworld = (TextView) view.findViewById(R.id.paihang_allworld_text);              //全服
            paihang_haoyou=(TextView)view.findViewById(R.id.paihang_haoyou_text);                      //好友
            paihang_name=(TextView)view.findViewById(R.id.paihang_name_text);                           //名字
            paihang_number=(TextView)view.findViewById(R.id.paihang_number);
        }
    }
    public PaihanAdapter(List<Person> list,List<PersonValue> list2){
        personList = list;
        personValueList=list2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from (parent.getContext() )
                .inflate (R.layout.paiming_item,parent,false );
        final ViewHolder holder = new ViewHolder ( view );
        //点击事件的监控和处理

        return holder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        Person person=personList.get(position);
        PersonValue personValue=personValueList.get(position);
        holder.hunmanImage.setImageResource(person.getPerson_img());
        holder.paihang_haoyou.setText("好友排名:"+personValue.getFriend_paihan());
        holder.paihang_allworld.setText("全球排名:"+personValue.getAllworld_paihan());
        holder.paihang_pingfeng.setText("综合评分:"+personValue.getFinally_value_pingfeng());
        holder.paihang_number.setText(String.valueOf(personValue.getFriend_paihan()));
        holder.paihang_name.setText(person.getPerson_name());

    }
    public int getItemCount(){
        return personList.size ();
    }
}