package ftd.txf.com.gamelife.utils;
import android.support.annotation.NonNull;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import ftd.txf.com.gamelife.entity.TimeGet;

public class DayFormater implements IAxisValueFormatter {
    private int d_value;
    @NonNull
    public void setValue( int value){
         this.d_value=value;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis){
        int position = (int) value;
        if (d_value==1){
            TimeGet timeGet=new TimeGet();
            String s=timeGet.getOldDate(position-6);
            String[] a=s.split("-");
            if (position==7){
                return "/号";
            }
            return a[2];
        }else {
            String s=(position*4)+"-"+(position*4+4)+"";
            if (position==6){
                return "/点";
            }
                return s;
        }
        }
}