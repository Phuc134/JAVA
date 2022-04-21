package com.example.a7minuteworkout

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

class ExerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    var progressbar: ProgressBar ?=null;
    var progressBarExercise :ProgressBar?= null
    var tvTimer: TextView?=null;
    var ivImage: ImageView?=null;
    var tvExerciseName: TextView?=null;
    var tvExerciseTimer: TextView?=null
    var llRestView: LinearLayout?=null
    var llExerciseView: LinearLayout?=null
    var tvUpcomingExercisename: TextView?=null
    private var restTimer: CountDownTimer? = null
    private var exerciseList: ArrayList<ExerciseModel>?=null
    private var currentExercisePosition =-1
    private var restProgress=0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress=0
    private var exerciseTimerDuration: Long =30
    private var pauseOffset: Long =0
    private var tts: TextToSpeech?= null
    private var player: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        this.progressbar = findViewById(R.id.progressBar);
        this.progressBarExercise = findViewById(R.id.progressBarExecise);
        this.tvTimer =findViewById(R.id.tvTimer);
        this.tvUpcomingExercisename =findViewById(R.id.tvUpcomingExerciseName);
        this.tvExerciseName =findViewById(R.id.tvExerciseName);
        this.ivImage =findViewById(R.id.ivImage);
        this.tvExerciseTimer =findViewById(R.id.tvExeciseTimer)
        this.llRestView =findViewById(R.id.llRestView)
        this.llExerciseView =findViewById(R.id.llExerciseView);
        var toolbar_exercise_activity=findViewById<Toolbar>(R.id.toolbar_exercise_activity)
        setSupportActionBar(toolbar_exercise_activity)
        //setSupportActionBar(findViewById(R.id.toolbar_exercise_activity))

        val actionBar= supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        };
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        tts= TextToSpeech(this,this)
        exerciseList=Constants.defaultExerciseList()

        setupRestView()

    }
    override fun onDestroy(){
        if (restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player != null){
            player!!.stop()
        }
        super.onDestroy()
    }
    private fun setResetProgressBar(){
        this.progressbar?.progress=restProgress
        restTimer = object:CountDownTimer(10000,1000)
        {
            override fun onTick(millisUtiFinished: Long) {
                restProgress++
                progressbar?.progress=10-restProgress
                tvTimer?.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                setupExerciseRestView()
            }

        }.start();
    }
    private fun setupRestView(){
        try {
            val soundURI =
                Uri.parse("android.resource://com.example.a7minuteworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player!!.isLooping = false // Sets the player to be looping or non-looping.
            player!!.start() // Starts Playback.
        } catch (e: Exception) {
            e.printStackTrace()
        }
        llRestView?.visibility=View.VISIBLE
        llExerciseView?.visibility=View.GONE
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        tvUpcomingExercisename?.text= exerciseList!![currentExercisePosition+1].getName()
        setResetProgressBar()
    }
    private fun setExerciseProgressBar(){
        this.progressBarExercise?.progress=exerciseProgress
        exerciseTimer = object:CountDownTimer(exerciseTimerDuration*1000,1000)
        {
            override fun onTick(millisUtiFinished: Long) {
                exerciseProgress++
                progressBarExercise?.progress=exerciseTimerDuration.toInt()-exerciseProgress
                tvExerciseTimer?.text=(exerciseTimerDuration.toInt()-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition<exerciseList?.size!!-1){
                    setupRestView()
                }
                else{
                    Toast.makeText(
                        this@ExerciseActivity,
                        "We will start the next rest screen",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }.start();
    }
    private fun setupExerciseRestView(){
        llRestView?.visibility=View.GONE
        llExerciseView?.visibility=View.VISIBLE
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress    =0
        }
        speakOut(exerciseList!![currentExercisePosition].getName())
        setExerciseProgressBar()
        ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName?.text=exerciseList!![currentExercisePosition].getName()
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result=tts!!.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "The Language specified is not supported!")}
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}
