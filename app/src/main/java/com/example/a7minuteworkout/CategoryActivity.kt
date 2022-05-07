package com.example.a7minuteworkout

import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import java.util.*
import javax.net.ssl.SSLEngineResult
import kotlin.collections.ArrayList
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
 class CategoryActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

//        var btnStart=findViewById<Button>(R.id.btnStart)
//        btnStart.setOnClickListener {
//            val intent = Intent(this,SecoundExercise::class.java)
//            startActivity(intent)
//
//        }
    }

     fun fullBody(view: View) {
         val intent=Intent(this,SecoundExercise::class.java)
         startActivity(intent)
     }
     fun belly(view: View) {
         val intent=Intent(this,ExerciseActivity::class.java)
         startActivity(intent)
     }

     fun hand(view: View) {
         val intent=Intent(this,ExerciseActivity::class.java)
         startActivity(intent)
     }
     fun leg(view: View) {
         val intent=Intent(this,ExerciseActivity::class.java)
         startActivity(intent)
     }
     fun buttmuscles(view: View) {
         val intent=Intent(this,ExerciseActivity::class.java)
         startActivity(intent)
     }


 }