package ftd.txf.com.gamelife.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ftd.txf.com.gamelife.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class SplashActivity extends AppCompatActivity {
    private CountDownTimer mTimer;
    private int text1=0;
    private String[] s=new String[]{"让","生","活","充","满","乐","趣",""};
    private String textString="";
    private TextView titletext;
    private GifDrawable gifDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);//去掉标题
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        GifImageView gifImageView = (GifImageView) findViewById(R.id.splash_gif);
        gifDrawable = (GifDrawable) gifImageView.getDrawable();
        titletext=(TextView)findViewById(R.id.splash_text);
        gifDrawable.setLoopCount(1);
        gifDrawable.stop();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        },1000);


    }

    /**
     * 利用countdown的时差进行textview动画的演示
     */
    private void initView(){
        if (mTimer == null) {
            mTimer = new CountDownTimer((long) (3 * 1000), 400) {
                @Override
                public void onTick(long millisUntilFinished) {

                    if (text1==0){
                        gifDrawable.start();
                    }
                    if (text1<7){
                        textString=textString+s[text1]+"    ";
                        text1++;
                        titletext.setText(textString);
                    }
                }

                @Override
                public void onFinish() {
                Intent intent = new Intent(SplashActivity.this,PersonCreateActivity.class);	//第二个参数即为执行完跳转后的Activity
                startActivity(intent);
                SplashActivity.this.finish();   //关闭splashActivity，将其回收，否则按返回键会返回此界面
                }
            };
            mTimer.start();
        }
    }
}
