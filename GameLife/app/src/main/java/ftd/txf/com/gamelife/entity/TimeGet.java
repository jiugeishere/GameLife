package ftd.txf.com.gamelife.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeGet {
    Calendar calendar = Calendar.getInstance();
    //获取系统的日期
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    public TimeGet(){

    }
    public int getYear() {
        year = calendar.get(Calendar.YEAR);
        return year;
    }
    public int getMonth() {
        month=calendar.get(Calendar.MONTH)+1;
        return month;
    }

    public int getDay() {
        day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public int getHour() {
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public int getMinute() {
        minute = calendar.get(Calendar.MINUTE);
        return minute;
    }

    public int getSecond() {
        second = calendar.get(Calendar.SECOND);
        return second;
    }

    /**
     * 格式化星期
     * @return
     */
    public String getFormatWeek(){
        String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int index=calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay=weeks[index-1];
        return weekDay;

    }
    /**
     * 格式化日，每号
     * @return
     */
    public String getDayS(int i){
        String[] s=new String[]{"","壹","贰","叁","肆","伍","陆","柒","捌","玖","拾"};
        String da="";
       if (i>=20){
           int y=i/10;
           i=i%10;
           da=s[y]+"拾"+s[i];
       }else if (i>10){
           int y=i/10;
           i=i%10;
           da="拾"+s[i];
       }else {
           da=s[i];
       }
       return da;
    }
    /**
     * 获取以前时间日期
     * @return
     */
    public String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        calendar.setTime(beginDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }
    /**
     * 格式化月
     * @return
     */
    public String getFormatMonth(int m){
        switch (m){
            case 1:
                return "一月";
            case 2:
                return "二月";
            case 3:
                return "三月";
            case 4:
                return "四月";
            case 5:
                return "五月";
            case 6:
                return "六月";
            case 7:
                return "七月";
            case 8:
                return "八月";
            case 9:
                return "九月";
            case 10:
                return "十月";
            case 11:
                return "十一月";
            case 12:
                return "十二月";
            default:
                return "任务列表";
        }
    }

    /**
     * 年月日格式
     * @return
     */
    public String getDayFormat(){
        return getYear()+"年"+getMonth()+"月"+getDay()+"日";
    }

    /**
     * 数值类型至天
     * @return
     */
    public int getToDay(){
        String m=String.valueOf(getMonth());
        String d=String.valueOf(getDay());
        if (getMonth()<10){
            m="0"+m;
        }
        if (getDay()<10){
            d="0"+d;
        }
        String s=String.valueOf(getYear())+m+d;
        return Integer.valueOf(s);
    }

    /**
     * 随机型BigID
     * @return
     */
    public int getBigID(){
         int i=getToDay()*100;
         i=i+getHour();
         return i;
    }

    /**
     * 格式化至分钟
     * @return
     */
    public String getToMinute(){
        String h=String.valueOf(getHour());
        String m=String.valueOf(getMinute());
        String s=String.valueOf(getToDay())+":"+h+":"+m;
        return s;
    }

    /**
     * 获取calendar
     * @return
     */
    public Calendar getCalendar(){
        return calendar;
    }

    public int TimetoNextDay(int hour,int minute){
        int time=0;
        if (hour<getHour()){
            time=(24-getHour()+hour)*60+minute-getMinute();
        }else if (getHour()>hour){
            time=(getHour())*60+minute+minute-getMinute();
        }else if (minute>getMinute()){
            time=minute-getMinute();
        }else {
            time=(24-getHour()+hour)*60+minute-getMinute();
        }
        time=time*60;
        return time;
    }
    /**
         * 获取两个日期之间的间隔天数
         * @return
         */
        public int getDayDistanceCount(int d,String day) {
            String s="";
            s=String.valueOf(d%100);
            d=d/100;
            s=String.valueOf(d%100)+"-"+s;
            d=d/100;
            s=String.valueOf(d)+"-"+s;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            try {
            startDate = sdf.parse(s);
            endDate = sdf.parse(day);
            } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }

            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.setTime(startDate);
            fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
            fromCalendar.set(Calendar.MINUTE, 0);
            fromCalendar.set(Calendar.SECOND, 0);
            fromCalendar.set(Calendar.MILLISECOND, 0);

            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTime(endDate);
            toCalendar.set(Calendar.HOUR_OF_DAY, 0);
            toCalendar.set(Calendar.MINUTE, 0);
            toCalendar.set(Calendar.SECOND, 0);
            toCalendar.set(Calendar.MILLISECOND, 0);
            return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
}
}


