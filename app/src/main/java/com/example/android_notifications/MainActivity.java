package com.example.android_notifications;

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
    public static final int REMINDER_NOTIFICATION_ID = 1213;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String CHANNEL_ID = getPackageName() + ".reminder";
        final Context context = this;
        Button notificationButton = findViewById(R.id.notification_button);
        final NotificationManager notificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFullScreen = new Intent(context,FullscreenActivity.class);
                goToFullScreen.putExtra(FullscreenActivity.FULL_SCREEN_STRING_1,"Notification Tapped");
                PendingIntent pendingIntent = PendingIntent.getActivity(context,0,goToFullScreen,PendingIntent.FLAG_ONE_SHOT);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(
                            CHANNEL_ID,
                            Constants.notificationName,
                            NotificationManager.IMPORTANCE_DEFAULT);

                    notificationChannel.setDescription(Constants.NOTIFICATION_DESCRIPTION);
                    notificationManager.createNotificationChannel(notificationChannel);
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                                    .setContentTitle(Constants.CONTENT_TITLE)
                                    .setSmallIcon(Constants.CONTENT_ICON)
                                    .setAutoCancel(true)
                                    .setContentIntent(pendingIntent);

                    notificationManager.notify(REMINDER_NOTIFICATION_ID, builder.build());
                }




            }


        });
    }
}
