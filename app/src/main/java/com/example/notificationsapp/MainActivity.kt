package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
          notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
      }
    }
}
