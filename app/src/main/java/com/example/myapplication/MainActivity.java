package com.example.myapplication;

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
    Context context;
    NotificationManager notificationManager;
    Button notification_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        notification_button = findViewById(R.id.notification_btn);
        notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String channelid = getPackageName() + ".button";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence        name         = getString(R.string.notification_name);
                    String              description  = getString(R.string.notification_desc);
                    int                 importance   = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel channel      = new NotificationChannel(channelid, name, importance);

                    channel.setDescription(description);
                    notificationManager.createNotificationChannel(channel);
                }

                Intent intent = new Intent(context, FullscreenActivity.class);
                intent.putExtra(getString(R.string.notification_fullscreen_key),"Notification Tapped");

                PendingIntent contentIntent = PendingIntent.getActivity(
                        context,
                        Constants.PENDING_INTENT_REQUEST_CODE,
                        intent,
                        PendingIntent.FLAG_ONE_SHOT);

                Intent actionIntent = new Intent(context, FullscreenActivity.class);
                actionIntent.putExtra(getString(R.string.notification_fullscreen_key), "Action Intent Notification");

                PendingIntent pendingActionIntent = PendingIntent.getActivity(
                        context,
                        Constants.PENDING_ACTION_INTENT_REQUEST_CODE,
                        actionIntent,
                        PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelid)
                        .setPriority(NotificationManager.IMPORTANCE_DEFAULT)//importance only affects versions 24 -> 26
                        .setContentTitle(getString(R.string.notification_button_title))
                        .setContentText(getString(R.string.notification_button_message))
                        .setColor(context.getResources().getColor(R.color.colorPrimary))
                        .setContentIntent(contentIntent)//icon color in notification bar
                        .addAction(R.drawable.ic_music_note_black_24dp, "Action Title",pendingActionIntent)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert);


                notificationManager.notify(Constants.NOTIFY_ID, builder.build());
                //extract notificationID (1) as a constant (usually in a constant class)
                //extract all strings as string resources


            }
        });


    }
}

