package com.example.jacob.android_notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID_INSTANT = 354;
    NotificationManager notificationManager;
    Context context;
    int notificationImportance = NotificationManager.IMPORTANCE_DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        findViewById(R.id.button_notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String channelId = getPackageName() + ".button";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "Notify Button Channel";
                    String description = "Notifications triggered by button";
                    NotificationChannel channel = new NotificationChannel(channelId, name, notificationImportance);
                    channel.setDescription(description);
                    notificationManager.createNotificationChannel(channel);
                }

                Intent intent = new Intent(context, FullscreenActivity.class);
                intent.putExtra(Constants.NOTIFICATION_KEY,"Notification Tapped");
                PendingIntent notifyPendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                        .setPriority(notificationImportance)
                        .setContentTitle("Button")
                        .setContentText("The button was pressed")
                        .setColor(context.getColor(R.color.colorPrimary))
                        .addAction(R.drawable.ic_launcher_foreground,"Go to full screen",notifyPendingIntent)
                        .setAutoCancel(true)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setDefaults(Notification.DEFAULT_ALL);
                notificationManager.notify(NOTIFICATION_ID_INSTANT, builder.build());







            }
        });

    }


}
