package com.example.androidnotifications

import android.app.PendingIntent
import android.content.Intent
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val NOTIFICATION_ID = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        get_notification.setOnClickListener {

            //TODO call the notification heer
            BasicNotification.BasicNotification(this)


            // put the string in next activity with a click
            val intent = Intent(this, FullscreenActivity::class.java)

            intent.putExtra("Text for Notification Clicks", "CLICKS")


            PendingIntent.getActivity (this, O, intent, PendingIntent.FLAG_ONE_SHOT  )






        }
    }
}
