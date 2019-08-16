package com.example.android_notifications
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationGenerator {
    //test
    val myTestText: String = "testing out my notification".toUpperCase()


    fun easyNotification(context: Context){
        val intent = Intent(context, FullscreenActivity::class.java)
        val pendingIntent = PendingIntent.getActivity (context,0, intent, PendingIntent.FLAG_ONE_SHOT)
        intent.putExtra("Notification Tapped", String())

        //Part 4 asks for another PendingIntent

        val pendingIntent2 = PendingIntent.getActivity (context,1, intent, PendingIntent.FLAG_ONE_SHOT)
        intent.putExtra("Notification Tapped", String())



        val channelId = "${context.packageName}.myChannel"
        val myNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val title = "My Notification Channel"

            //Experimenting here
            val importanceOfNotification = NotificationManager.IMPORTANCE_HIGH
            val channelDescription = "My created channel"
            val myChannel = NotificationChannel(channelId, title, importanceOfNotification)
            myChannel.description = channelDescription
            myNotificationManager.createNotificationChannel(myChannel)


        }

        val myNotificationBuilder = NotificationCompat.Builder(context, channelId)
                //Experiment here

            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentTitle("My notification")
            .setContentText("My doggo seems to think she is a person, when I hug my wife my dog climbs on me because she want's one as well")
            .setContentText(myTestText)
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setColor(16711680)
            .setAutoCancel(true)
            .setDefaults(1)
            .setContentIntent(pendingIntent)
            .addAction(android.R.drawable.alert_dark_frame, "second", pendingIntent2)



        myNotificationManager.notify(MainActivity.NOTIFICATION_ID_INSTANT, myNotificationBuilder.build())

    }

}