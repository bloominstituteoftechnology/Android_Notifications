package com.example.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val NOTIFICATION_ID = 87
        const val MESSAGE_ID = "46"
        const val OTHER_MESSAGE_ID = "84"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notification_btn.setOnClickListener {

            val intent = Intent(this, FullscreenActivity::class.java)
            intent.putExtra(MESSAGE_ID, "HELLO WORLD!")
            val pendingContentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            intent.putExtra(OTHER_MESSAGE_ID, "WHY")
            val pendingActionIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT)

            val notification = Notification(this, MainActivity::class.java)
            notification.notificationPriority = NotificationCompat.PRIORITY_LOW
            notification.setPendingContentIntent(pendingContentIntent)
            notification.addActionIntent(R.drawable.ic_launcher_background, "second intent", pendingActionIntent)

            notification.notify(NOTIFICATION_ID, getString(R.string.notification_title_main),
                getString(R.string.notification_content_main))
        }
    }
}
