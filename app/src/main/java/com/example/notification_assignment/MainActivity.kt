package com.example.notification_assignment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager

        NOTI_btn.setOnClickListener { p0 ->

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {

                val channelId = "channel_ID"
                val name = "simple notification"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val description = "Hello this is the discription"
                val channel = NotificationChannel(channelId,name,importance)
                channel.description = description
                
            }




        }





    }



}
