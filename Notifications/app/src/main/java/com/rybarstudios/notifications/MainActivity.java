package com.rybarstudios.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static java.lang.Package.getPackage;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID_INSTANT = 777;
    Context context;
    static String channelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        channelId = getPackageName() + ".reminder";

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final NotificationManager notificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "This is a test notification";
                    String description = "Just testing this";
                    int importance = NotificationManager.IMPORTANCE_LOW;

                    NotificationChannel notificationChannel = new NotificationChannel(channelId, name, importance);
                    notificationChannel.setDescription(description);

                    notificationManager.createNotificationChannel(notificationChannel);


                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                            .setPriority(NotificationManager.IMPORTANCE_LOW)
                            .setContentTitle(name)
                            .setContentText(description)
                            .setSmallIcon(R.drawable.ic_android_green_24dp)
                            .setColor(getColor(android.R.color.holo_green_light))
                            .setDefaults(Notification.DEFAULT_ALL);

                    notificationManager.notify(NOTIFICATION_ID_INSTANT, builder.build());

                }
            }
        });
    }
}
