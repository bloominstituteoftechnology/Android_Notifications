package com.lambdaschool.android_notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationManager notifMgr=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        Button buttonGetNotif=findViewById(R.id.button_get_notification);
        buttonGetNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {
                    NotificationChannel notifChannel = new NotificationChannel(getPackageName()+ ".testing","Basil's Channel", NotificationManager.IMPORTANCE_DEFAULT);
                    notifChannel.setDescription("This is a temporary channel for conducting tests");
                    notifMgr.createNotificationChannel(notifChannel);
                    NotificationCompat.Builder notifCompatBuilder=new NotificationCompat.Builder(v.getContext(), getPackageName()+".testing");
                    notifCompatBuilder.setPriority(NotificationManager.IMPORTANCE_DEFAULT);
                    notifCompatBuilder.setContentTitle("Basil's Channel");
                    notifCompatBuilder.setContentText("Pay attention to the various elements in play.");
                    notifCompatBuilder.setSmallIcon(R.drawable.ic_wb_cloudy_black_24dp);
                    notifCompatBuilder.setColor(Color.BLUE);
                    notifMgr.notify(7896575, notifCompatBuilder.build());
                }
            }
        });
    }
}
