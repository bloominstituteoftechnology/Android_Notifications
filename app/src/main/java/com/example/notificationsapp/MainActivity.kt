package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val NOTIFICATION_ID = 62
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val channelId = "${this.packageName}.BasicChannel"
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


      button_getNotification.setOnClickListener {

          val intent= Intent(this,FullscreenActivity::class.java)
          intent.putExtra("fullScreenText","Notification Tapped")
          val intentTwo= Intent(this,FullscreenActivity::class.java)
          intentTwo.putExtra("fullScreenText","Notification Action Tapped")
          val pendingContentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
          val secondPendingContentIntent = PendingIntent.getActivity(this, 1, intentTwo, PendingIntent.FLAG_ONE_SHOT)
          if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
              val name = "Basic Notification Channel"
              val importance = NotificationManager.IMPORTANCE_HIGH
              val description = "Basic notification channel"
              val channel = NotificationChannel(channelId, name, importance)
              channel.description = description
              notificationManager.createNotificationChannel(channel)
          }
          val notificationBuilder = NotificationCompat.Builder(this, channelId)
              .setPriority(NotificationManager.IMPORTANCE_HIGH)
              .setContentTitle("Basic Notification")
              .setContentText("Welcome to Ali's Notification Project")
              .setSmallIcon(android.R.drawable.presence_online)
              .setColor(ContextCompat.getColor(this, R.color.colorAccent))
              .setDefaults(Notification.DEFAULT_SOUND)
              .setContentIntent(pendingContentIntent)
              .addAction(R.drawable.abc_list_pressed_holo_light,"Notification Action",secondPendingContentIntent)
              .setAutoCancel(true)
          notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
      }
    }
}
