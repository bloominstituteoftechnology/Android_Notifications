package com.example.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "5467811";
    private static final int NOTIFICATION_ID = 1515115;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final int requestID = (int) System.currentTimeMillis();
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateNotification = findViewById(R.id.button_create_notification);
        Button buttonSendString = findViewById(R.id.button_send_string);

        //Simple notification sender
        buttonCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_lightbulb_outline_blue_24dp)
                        .setContentTitle("Simple Notification")
                        .setContentText("Come check out such and such things")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                createNotificationChannel();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(view.getContext());
                // notificationId(123) is a unique int for each notification
                notificationManager.notify(123, builder.build());
            }
        });

        //Intent w/ string intent sender
        buttonSendString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setNotification("This is a cool string");

                /*Intent notifyIntent = new Intent(getApplicationContext(), StringDisplay.class);
                notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(view.getContext());
                stackBuilder.addParentStack(StringDisplay.class);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(requestID, PendingIntent.FLAG_UPDATE_CURRENT);
                notifyIntent.putExtra("STRING", "Keep calm and Code on");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_lightbulb_outline_blue_24dp)
                        .setContentTitle("You Have A New Message")
                        .setContentText("Let's see what it says")
                        .setContentIntent(resultPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                createNotificationChannel();


                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(view.getContext());
                // notificationId(123) is a unique int for each notification
                notificationManager.notify(124, builder.build());*/

            }
        });



    }

    private void setNotification(String notificationMessage) {

        int requestID = (int) System.currentTimeMillis();


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), StringDisplay.class);
        intent.putExtra("STRING", "String to be Opened in second Activity");




        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), requestID, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_lightbulb_outline_blue_24dp)
                .setContentTitle("Simple Notification")
                .setContentText(notificationMessage)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        createNotificationChannel();
        //mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}