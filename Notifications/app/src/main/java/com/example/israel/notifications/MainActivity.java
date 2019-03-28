package com.example.israel.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "id:1";
    public static final String NOTIFICATION_NAME = "name:1";
    public static final String KEY_NOTIFICATION = "notification";
    public static final int REQUEST_NOTIFICATION_TAPPED = 0;
    public static final int REQUEST_NOTIFICATION_SNOOZE = 1;
    private static int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getNotificationButton = findViewById(R.id.button_get_notification);
        getNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // channel
                    NotificationManager notificationManager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
                    NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH);
                    channel.setDescription("desc: This is an example notification");
                    notificationManager.createNotificationChannel(channel);

                    // builder
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, NOTIFICATION_CHANNEL_ID)
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .setContentTitle("title: Example title")
                            .setContentText("text: Example text")
                            .setSmallIcon(R.drawable.ic_notification_and2)
                            .setColor(Color.argb(255, 255, 255, 0))
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

                    // this is for when the notification is tapped anywhere
                    Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
                    intent.putExtra(KEY_NOTIFICATION, "Notification tapped");
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, REQUEST_NOTIFICATION_TAPPED, intent, PendingIntent.FLAG_ONE_SHOT);
                    builder.setContentIntent(pendingIntent);

                    // this is for when the notification's action is tapped. In this case, the snooze
                    Intent snoozeIntent = new Intent(MainActivity.this, FullscreenActivity.class);
                    snoozeIntent.putExtra(KEY_NOTIFICATION, "Snooze");
                    PendingIntent pendingIntent1Snooze = PendingIntent.getActivity(MainActivity.this, REQUEST_NOTIFICATION_SNOOZE, snoozeIntent, PendingIntent.FLAG_ONE_SHOT);
                    /**
                     * It seems that icon for addAction is no longer displayed from Nougat and so on.
                     * But it is still required to pass one
                     * https://stackoverflow.com/questions/41503972/icon-is-not-getting-displayed-in-notification-in-android-nougat
                     * */
                    builder.addAction(R.drawable.ic_notification_action_snooze, "Snooze", pendingIntent1Snooze);

                    notificationManager.notify(notificationId++, builder.build());
                }

            }
        });
    }
}
