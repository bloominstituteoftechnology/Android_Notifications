package com.jakeesveld.android_notifications;

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

    public static final int NOTIFICATION_ID = 526;
    public static final int PENDING_INTENT_REQUEST_CODE = 786;
    public static final int COOLER_INTENT_REQUEST_CODE = 5948;
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


        findViewById(R.id.button_send_notification_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channelID = getPackageName() + "button";
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = "Notifications";
                    String description = "These are the notifications we send you";
                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel channel = new NotificationChannel(channelID, name, importance);
                    channel.setDescription(description);

                    notificationManager.createNotificationChannel(channel);
                }

                Intent clickIntent = new Intent(context, FullscreenActivity.class);
                clickIntent.putExtra("String", "This is a string");
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        PENDING_INTENT_REQUEST_CODE,
                        clickIntent,
                        PendingIntent.FLAG_ONE_SHOT
                );

                Intent actionIntent = new Intent(context, FullscreenActivity.class);
                actionIntent.putExtra("String", "This is a cooler string.");
                PendingIntent actionPendingIntent = PendingIntent.getActivity(
                        context,
                        COOLER_INTENT_REQUEST_CODE,
                        actionIntent,
                        PendingIntent.FLAG_ONE_SHOT
                );

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                        .setContentTitle("Another Notification")
                        .setContentText("This one is even cooler! Click here!")
                        .setColor(getResources().getColor(R.color.colorAccent))
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.ic_launcher_foreground, "No Click Me! I'm cooler", actionPendingIntent);

                notificationManager.notify(NOTIFICATION_ID, builder.build());

            }
        });
    }
}
