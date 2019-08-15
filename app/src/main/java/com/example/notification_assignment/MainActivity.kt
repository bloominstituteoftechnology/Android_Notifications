package com.example.notification_assignment

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

   companion object {
       const val NOTIF_KEY = 5
       const val NOTIF_Stringkey = "description200"
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager

        NOTI_btn.setOnClickListener { p0 ->

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channelId = "channel_ID"
                val name = "super basic notification"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val description = "Hello this is the discription"
                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description

                notificationManager.createNotificationChannel(channel)

                val SwitchToFull = Intent(this, FullscreenActivity::class.java)
                intent.putExtra("descrip", NOTIF_Stringkey)
                val pending_in = PendingIntent.getActivity(this,0,SwitchToFull,PendingIntent.FLAG_ONE_SHOT)

                val notif_builder = NotificationCompat.Builder(this, channelId)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setContentTitle("Basic notification!")
                    .setContentText("Sample Text")
                    .setSmallIcon(android.R.drawable.btn_star_big_on)
                    .setColor(getColor(R.color.colorPrimary))
                    .setDefaults(Activity.DEFAULT_KEYS_DIALER)
                    .setContentIntent(pending_in)
                notificationManager.notify(NOTIF_KEY, notif_builder.build())




            }

        }





    }



}
