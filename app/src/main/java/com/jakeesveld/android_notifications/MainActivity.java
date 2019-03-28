package com.jakeesveld.android_notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 526;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        final NotificationManager notificationManager =(NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        findViewById(R.id.button_send_notification_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channelID = getPackageName() + "button";
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = "Notifications";
                    String description = "These are the notifications our app will send you";
                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel channel = new NotificationChannel(channelID, name, importance);
                    channel.setDescription(description);

                    notificationManager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                        .setContentTitle("Jake's Notifications")
                        .setContentText("You were just sent a notification from me!(You)")
                        .setSmallIcon(android.R.drawable.gallery_thumb)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setColor(getResources().getColor(R.color.colorPrimary))
                        .setDefaults(Notification.DEFAULT_ALL);

                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
        });
    }
}
