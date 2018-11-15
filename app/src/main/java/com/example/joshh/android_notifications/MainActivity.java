package com.example.joshh.android_notifications;

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
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID_INSTANT = 0;
    private Context context;
    NotificationManager notificationManager;

    private Button getNotificationButton;
    private PendingIntent pendingIntent;
    private PendingIntent actionPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        getNotificationButton = findViewById(R.id.get_notification_button);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullscreenActivity.class);
                intent.putExtra("notification", "Notification Tapped");
                pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                intent.putExtra("notification", "Tapped Action Button");
                actionPendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_ONE_SHOT);

                String channelId = getPackageName() + getString(R.string.channel_id_suffix);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = getString(R.string.notification_channel_name);
                    String description = getString(R.string.notification_channel_desc);
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                    channel.setDescription(description);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("High importance")
                        .setContentText("This notification was created by a button")
                        .setColor(getResources().getColor(R.color.colorAccent))
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .addAction(R.drawable.ic_launcher_foreground, "Launch", actionPendingIntent);
                notificationManager.notify(NOTIFICATION_ID_INSTANT, builder.build());
            }
        });
    }
}
