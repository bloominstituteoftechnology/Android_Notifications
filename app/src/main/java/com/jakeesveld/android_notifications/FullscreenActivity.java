package com.jakeesveld.android_notifications;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class FullscreenActivity extends AppCompatActivity {
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        
        textViewData = findViewById(R.id.text_view_data);

        Intent intent = getIntent();
        String data = intent.getStringExtra("String");

        textViewData.setText(data);


    }
}
