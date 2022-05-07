package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a7minuteworkout.fragments.chart
import com.example.a7minuteworkout.fragments.history
import com.example.a7minuteworkout.fragments.person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val personFragment=person()
    private val chartFragment=chart()
    private val historyFragment=history()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar_exercise_activity))
        replaceFragmaent(personFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_person->replaceFragmaent(personFragment)
                R.id.ic_chart->replaceFragmaent(chartFragment)
                R.id.ic_history->replaceFragmaent(historyFragment)
            }
            true
        }
        var llStart: LinearLayout?=null;
        llStart = findViewById(R.id.llStart)
        llStart.setOnClickListener {
            val intent = Intent(this,CategoryActivity::class.java)
            startActivity(intent)

        }

    }
    private fun replaceFragmaent(fragment:Fragment){
        if(fragment!=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_contaiber,fragment)
            transaction.commit()
        }
    }

    fun person_click(view: View) {
        val intent=Intent(this,CategoryActivity::class.java)
        startActivity(intent)
    }
}