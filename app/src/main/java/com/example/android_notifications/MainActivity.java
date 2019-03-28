package com.example.android_notifications;

import android.app.NotificationManager;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "5467811";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateNotification = findViewById(R.id.button_create_notification);
        buttonCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext(), CHANNEL_ID)
                        .setContentTitle("Simple Notification")
                        .setContentText("Come check out such and such thing")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            }
        });
    }
}
