package ftd.txf.com.gamelife.utils;

import java.util.ArrayList;
import java.util.List;

import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.entity.TimeGet;
import ftd.txf.com.gamelife.entity.Work;

public class WorksGet {
    private List<Work> xtlists=new ArrayList<>();

    public List<Work> getXiTongdata(int way){
//        List<Work> xtlists= Arrays.asList(new Work(null,R.drawable.monster_renzheng,"认真怪","认真学习30分钟",30,60,12,"智","倒计时","学习","简单",null,null,null,null,null),
//                new Work(null,R.drawable.monster_renzheng,"认真怪","课内复习40分钟",40,80,16,"智","倒计时","学习","一般",null,null,null,null,null),
//                new Work(null,R.drawable.monster_renzheng,"认真怪","课内复习40分钟",40,80,16,"智","倒计时","学习","一般",null,null,null,null,null));

        xtlists.clear();
        TimeGet timeGet=new TimeGet();
        switch (way){
            case 1:
                xtlists.add(new Work(-1L,R.drawable.monster_renzheng,"认真怪","认真学习30分钟",30,60,12,"智","倒计时","学习",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_renzheng,"认真怪","课内复习40分钟",40,80,16,"智","倒计时","学习",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_renzheng,"认真怪","课外拓展35分钟",35,70,12,"智","倒计时","学习",1L,timeGet.getToMinute()));
            case 2:
                xtlists.add(new Work(-1L,R.drawable.monster_rexue,"热血怪","俯卧撑15分钟",15,45,7,"体","倒计时","锻炼",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_rexue,"热血怪","仰卧起坐40个 4组",10,30,5,"体","倒计时","锻炼",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_rexue,"热血怪","平板支撑5分钟 4组",20,60,10,"体","倒计时","锻炼",1L,timeGet.getToMinute()));
            case 3:
                xtlists.add(new Work(-1L,R.drawable.monster_zhilv,"自律怪","早起打卡",0,120,30,"德","不计时","拓展",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_zhilv,"自律怪","多喝开水",0,120,30,"德","不计时","拓展",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_zhilv,"自律怪","少玩手机",0,120,30,"德","不计时","拓展",1L,timeGet.getToMinute()));
            case 4:
                xtlists.add(new Work(-1L,R.drawable.monster_renzheng,"认真怪","认真学习30分钟",30,60,120,"智","倒计时","学习",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_rexue,"热血怪","俯卧撑15分钟",15,45,75,"体","倒计时","锻炼",1L,timeGet.getToMinute()));
                xtlists.add(new Work(-1L,R.drawable.monster_zhilv,"自律怪","早起打卡",0,120,30,"德","不计时","拓展",1L,timeGet.getToMinute()));
        }

        return xtlists;
    }
}
