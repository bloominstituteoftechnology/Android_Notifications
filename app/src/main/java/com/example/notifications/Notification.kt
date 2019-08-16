package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class Notification<T>(private val context: Context, private val actingClass : Class<T>,
                      private val icon : Int = R.drawable.ic_launcher_foreground){

    private val channelId = "{$context.packageName}.simplechannel"

    private val notificationBuilder = NotificationCompat.Builder(context, channelId)
    var channelImportance = NotificationManager.IMPORTANCE_DEFAULT
    var notificationPriority =  NotificationCompat.PRIORITY_DEFAULT
    var toAutoCancel = true
    //var pendingContentIntent : PendingIntent = PendingIntent.getActivity(context, 0, Intent(), PendingIntent.FLAG_ONE_SHOT)

    fun notify(classID : Int = -1,  contentTitle : String = "", contentText : String = ""){

        if(classID != -1) { //needs a class ID to call a notification

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            createNotificationChannel(notificationManager)

            notificationBuilder.setSmallIcon(icon)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setPriority(notificationPriority)
                .setAutoCancel(toAutoCancel)

            notificationManager.notify(classID, notificationBuilder.build())
        }
    }

    private fun createNotificationChannel(notificationManager : NotificationManager){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionT = context.getString(R.string.channel_description)

            val channel = NotificationChannel(channelId, name, channelImportance).apply {
                description = descriptionT
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun setPendingContentIntent(pendingContentIntent: PendingIntent){
        notificationBuilder.setContentIntent(pendingContentIntent)
    }

    fun addActionIntent(icon: Int, content : String, pendingActionIntent : PendingIntent){
        notificationBuilder.addAction(icon, content, pendingActionIntent)
    }
}