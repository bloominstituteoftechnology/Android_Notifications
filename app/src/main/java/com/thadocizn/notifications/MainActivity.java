package com.thadocizn.notifications;

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

    public static final String BUTTON_PRESSED = "button_pressed";
    NotificationManager notificationManager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channelID = getPackageName() + ".button";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = "Demo Button";
                    String description = "Notification";
                    int importance =  NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel channel = new NotificationChannel(channelID, name, importance);
                    channel.setDescription(description);

                    notificationManager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setContentTitle(channelID)
                        .setContentText("The button was pressed")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setColor(context.getResources().getColor(R.color.colorPrimaryDark))
                        .setDefaults(Notification.DEFAULT_ALL);

                notificationManager.notify(1, builder.build());
                String buttonPressed = "The button was pressed";

                Intent intent = new Intent(context, FullscreenActivity.class);
                intent.putExtra(BUTTON_PRESSED, buttonPressed);
                PendingIntent.getActivity(context,0, intent, PendingIntent.FLAG_ONE_SHOT);
            }
        });
    }
}
