package com.example.joshh.android_notifications;

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
    public static final int NOTIFICATION_ID_INSTANT = 0;
    private Context context;
    NotificationManager notificationManager;

    private Button getNotificationButton;

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
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("High importance")
                        .setContentText("This notification was created by a button")
                        .setColor(getResources().getColor(R.color.colorPrimaryDark))
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE);
                notificationManager.notify(NOTIFICATION_ID_INSTANT, builder.build());
            }
        });
    }
}
