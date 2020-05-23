package ftd.txf.com.gamelife.services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import ftd.txf.com.gamelife.R;
import ftd.txf.com.gamelife.activity.MainActivity;

public class RemindActionService extends Service {
    private Context mContext;
    private NotificationManager notificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String title=intent.getStringExtra("title");
        String contentText=intent.getStringExtra("contentText");
        int id=intent.getIntExtra("channel",1);
       createNotificationChannel("gamelifechat",contentText, NotificationManager.IMPORTANCE_DEFAULT);
//发送通知
        sendNotification(title,contentText,"gamelifechat",id);
        return START_REDELIVER_INTENT;
    }
    /**
     * 8.0以上手机需要构建通知渠道，才能够打开通知栏
     * @param channelId 通知栏id
     * @param channelName 通知栏名
     * @param importance 通知栏级别 例如NotificationManager.IMPORTANCE_HIGH;
     */
    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannel(String channelId, String channelName, int importance) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            notificationManager = (NotificationManager) mContext.getSystemService(
                    NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }else{
            Log.e("渠道创建","Android版本低于26，无需创建通知渠道");
        }
    }
    public void sendNotification(String title,String content,String notificationChannel,int id){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext,notificationChannel).setSmallIcon(R.drawable.gamelife_icon)
                // 设置图标
                .setContentTitle(title)
                .setContentText(content)
                .setTicker(mContext.getString(R.string.app_name)+"为您推送了一条新的信息\n" + content)
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                //              .setOngoing(true)
                .setFullScreenIntent(null, false)
                .setDefaults(Notification.DEFAULT_ALL)
                //              .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_MAX);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("isMesg", true);
        resultIntent.setClass(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(id, notification);
    }
}
