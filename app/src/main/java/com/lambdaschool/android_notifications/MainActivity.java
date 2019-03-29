package com.lambdaschool.android_notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationManager notifMgr = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Button buttonGetNotif = findViewById(R.id.button_get_notification);
        buttonGetNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    Intent intent = new Intent(v.getContext(), FullscreenActivity.class);
//                    intent.putExtra("notification", "This is a test of Basil's craftiness!");
//                    PendingIntent pendingIntent = PendingIntent.getActivity(v.getContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
                    Intent intent2 = new Intent(v.getContext(), FullscreenActivity.class);
                    intent2.putExtra("notification", "This is a second test of Basil's skills!");
                    PendingIntent pendingIntent2 = PendingIntent.getActivity(v.getContext(), 1, intent2, PendingIntent.FLAG_ONE_SHOT);

                    RemoteInput remoteInput = new RemoteInput.Builder("new_test").setLabel("Enter the secret code").build();
                    NotificationCompat.Action notifCompatAction = new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_edit, "Test", pendingIntent2).addRemoteInput(remoteInput).setAllowGeneratedReplies(true).build();

                    NotificationChannel notifChannel = new NotificationChannel(getPackageName() + ".testing", "Basil's Channel", NotificationManager.IMPORTANCE_DEFAULT);
                    notifChannel.setDescription("This is a temporary channel for conducting tests");
                    notifMgr.createNotificationChannel(notifChannel);
                    NotificationCompat.Builder notifCompatBuilder = new NotificationCompat.Builder(v.getContext(), getPackageName() + ".testing");
                    notifCompatBuilder.setPriority(NotificationManager.IMPORTANCE_DEFAULT);
                    notifCompatBuilder.setContentIntent(pendingIntent2);
                    notifCompatBuilder.setContentTitle("Basil's Channel");
                    notifCompatBuilder.setContentText("Pay attention to the various elements in play.");
                    notifCompatBuilder.setSmallIcon(R.drawable.ic_wb_cloudy_black_24dp);
                    notifCompatBuilder.setColor(Color.BLUE);
                    notifCompatBuilder.addAction(notifCompatAction);
                    notifMgr.notify(7896575, notifCompatBuilder.build());
                }
            }
        });
    }
}
