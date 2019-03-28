package com.vivekvishwanath.android_notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button notificationButton;
    static final int TEXT_NOTIFICATION_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationButton = findViewById(R.id.notification_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification Test";
            String description = "This channel is used to test sending a notification";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(getPackageName(), name, importance);

            notificationManager.createNotificationChannel(channel);
        }

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, getPackageName()).
                        setPriority(NotificationManager.IMPORTANCE_HIGH).
                        setContentTitle("Test Notification")
                        .setContentText("This is a test notification")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setColor(getResources().getColor(android.R.color.holo_blue_dark))
                        .setDefaults(Notification.DEFAULT_ALL);

                notificationManager.notify(MainActivity.TEXT_NOTIFICATION_ID, builder.build());
            }
        });
    }

}
