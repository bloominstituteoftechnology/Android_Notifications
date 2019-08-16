package com.example.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val NOTIFICATION_ID = 87
    }

    private val notification = Notification(this, MainActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notification_btn.setOnClickListener {
            notification.notify(NOTIFICATION_ID, getString(R.string.notification_title_main),
                getString(R.string.notification_content_main))
        }

    }
}
