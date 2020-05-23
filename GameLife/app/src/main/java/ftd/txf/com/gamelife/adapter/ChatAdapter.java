package ftd.txf.com.gamelife.adapter;

import android.content.Intent;
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

public class ChatAdapter extends RecyclerView.Adapter <ChatAdapter.ViewHolder>{
    private List<Integer> chatList;
    private List<Integer> ilist=new ArrayList<>();
    static class ViewHolder  extends RecyclerView.ViewHolder{
        View chatView;
        private ImageView hunmanImage;
        private TextView human_name;
        private TextView human_pingfeng;
        private TextView human_rank;
        private TextView chat_time;
        private TextView talk_cont;
        private TextView talk_content;
        private ImageView chat_image1;
        private ImageView chat_message_button;
        private ImageView chat_love;
        public ViewHolder(View view){
            super(view);
            chatView = view;
            hunmanImage = (ImageView) view.findViewById(R.id.chat_card_touxiang);                        //图片
            human_name=(TextView)view.findViewById(R.id.chat_card_name);
            human_pingfeng=(TextView)view.findViewById(R.id.chat_card_pingf);
            human_rank=(TextView)view.findViewById(R.id.chat_card_rank);
            chat_time=(TextView)view.findViewById(R.id.chat_card_time);
            talk_cont=(TextView)view.findViewById(R.id.chat_card_love_hunmantimes);
            talk_content=(TextView)view.findViewById(R.id.chat_card_content);
            chat_message_button=(ImageView)view.findViewById(R.id.action_chat_message);
            chat_image1 = (ImageView) view.findViewById(R.id.chat_card_image);
            chat_love = (ImageView) view.findViewById(R.id.chat_card_love_button);
        }
    }
    public ChatAdapter(List<Integer> list){
        chatList = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View view = LayoutInflater.from (parent.getContext() )
                .inflate (R.layout.chat_item,parent,false );
        final ViewHolder holder = new ViewHolder ( view );
        holder.chat_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choose=0;
                for (int i:ilist){
                    if (i==holder.getAdapterPosition()){
                        choose=1;
                        break;
                    }
                }
                if (choose==0){
                    int y=Integer.valueOf(holder.talk_cont.getText().toString());
                    holder.chat_love.setBackgroundResource(R.drawable.love_block);
                    y++;
                    holder.talk_cont.setText(String .valueOf(y));
                }
                ilist.add(holder.getAdapterPosition());
            }
        });
       // 点击事件的监控和处理
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        holder.hunmanImage.setImageResource(R.drawable.touxiang1);
        holder.human_name.setText("一号选手");
        holder.human_rank.setText("等级：15");
        holder.human_pingfeng.setText("综合评分：1450");
        holder.chat_time.setText(chatList.get(position)+"小时前");
        holder.talk_cont.setText("99");
        holder.talk_content.setText("把眼泪擦干，站起来吧，伟大的人呐！            不惧一切，迎面向前！");
    }
    public int getItemCount(){
        return chatList.size ();
    }
}