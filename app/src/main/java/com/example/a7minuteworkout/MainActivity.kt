package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar_exercise_activity))
        var llStart: LinearLayout?=null;
        llStart = findViewById(R.id.llStart)
        llStart.setOnClickListener {
            val intent = Intent(this,CategoryActivity::class.java)
            startActivity(intent)

        }

    }

}