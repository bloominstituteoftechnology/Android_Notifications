package com.lambda.android_notifications;

import android.app.NotificationManager;

import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.NotificationCompat;

import android.support.v4.app.RemoteInput;
import android.widget.TextView;


public class NotificationResponseReceiver extends BroadcastReceiver {



    @Override

    public void onReceive(Context context, Intent intent) {
        String channelId = "CHANNEL_ID";
        String entryText = processResponse(intent, context);

        if(entryText != null){
            TextView tv=new TextView( context );
         //   tv.setText(  );

        }

    }



    private String processResponse(Intent intent, Context context){

        Bundle input = RemoteInput.getResultsFromIntent(intent);
        String channelId = "CHANNEL_ID";
        if(input != null){

            String entryText = input.getCharSequence("NEW_ENTRY_ACTION").toString();



            NotificationCompat.Builder successNotification = new NotificationCompat.Builder(

                    context, channelId)

                    .setSmallIcon(android.R.drawable.ic_menu_save)

                    .setContentText("New Entry Created");



            NotificationManager notificationManager = (NotificationManager)

                    context.getSystemService(Context.NOTIFICATION_SERVICE);



            notificationManager.notify(1, successNotification.build());



            return entryText;

        }

        return null;

    }



}