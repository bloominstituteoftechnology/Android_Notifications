package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Get_Notification.setOnClickListener {p0 ->
            val notificationManger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "$packageName.projectchannel"


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
               val name = "Notification Channel"
               val importance = NotificationManager.IMPORTANCE_HIGH
               val description = "What comes up when you get notification"
               val channel = NotificationChannel(channelId,name,importance)
                channel.description = description
            }
      }
    }
}
