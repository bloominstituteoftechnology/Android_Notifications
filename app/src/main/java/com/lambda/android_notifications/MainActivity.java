package com.lambda.android_notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        context=getApplicationContext();
        context.getSystemService(Context.NOTIFICATION_SERVICE);
        findViewById( R.id.button_get_notification ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channelId = "CHANNEL_ID";
                String name = "CHANNEL_NAME";
                int importance = NotificationManager.IMPORTANCE_HIGH;


               if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                   NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){



                       String description = "Notification for journal entry";





                       NotificationChannel notificationChannel = new NotificationChannel(channelId, name, importance);

                       notificationChannel.setDescription(description);

                       notificationManager.createNotificationChannel(notificationChannel);

                   }



                   android.support.v4.app.RemoteInput remoteInput = new RemoteInput.Builder("NEW_ENTRY_ACTION")

                           .setLabel("Enter your entry text")

                           .build();



                   Intent inputIntent = new Intent(context, MainActivity.class);

                   PendingIntent resultPendingIntent = PendingIntent.getActivity

                           (

                                   context,

                                   101,

                                   inputIntent,
                                   PendingIntent.FLAG_ONE_SHOT
                                   //PendingIntent.FLAG_UPDATE_CURRENT

                           );



                   NotificationCompat.Action inputAction = new NotificationCompat.Action.Builder(

                           android.R.drawable.ic_menu_edit, "Entry", resultPendingIntent)

                           .addRemoteInput(remoteInput)

                           .setAllowGeneratedReplies(true)

                           .build();



                   NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)

                           .setPriority(NotificationManager.IMPORTANCE_LOW)

                           .setContentTitle("Journal Entry")

                           .setContentText("Create a journal entry")

                           .setSmallIcon(R.drawable.ic_launcher_foreground)

                           .addAction(inputAction)

                           .setColor(getResources().getColor(R.color.colorAccent));



                   notificationManager.notify(1
                           , builder.build());




                }
            }
        } );

    }
}
