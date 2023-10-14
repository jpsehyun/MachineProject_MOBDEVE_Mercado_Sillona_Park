package com.example.machineproject_mobdeve_mercado_sillona_park

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class fridayActivity : AppCompatActivity() {
    private val characterList: ArrayList<Course> = courseGenerator.generateData()
    private lateinit var recyclerView: RecyclerView
    private lateinit var fri_Home_Btn: Button

    var x1: Float = 0.0f
    var x2: Float = 0.0f
    val MIN_DISTANCE = 300

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> x1 = event.x
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                val deltaX = x2 - x1
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        loadPrevScreen()
                    } else {
                        loadNextScreen()

                    }
                } else {
                    // consider as something else - a screen tap for example
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friday_schedule_view)

        this.recyclerView = findViewById(R.id.fri_RecyclerView)

        this.recyclerView.adapter = Adapter(this.characterList, "Friday")

        this.recyclerView.layoutManager = LinearLayoutManager(this)

        initializeUI()
        initializeListener()
    }

    fun initializeUI() {
        fri_Home_Btn = findViewById(R.id.fri_Home_Btn)
    }

    fun initializeListener() {
        fri_Home_Btn.setOnClickListener {
            loadHomeScreen()
        }
    }

    private fun loadNextScreen() {
        val intentLoadNextActivity = Intent(this, saturdayActivity::class.java)
        startActivity(intentLoadNextActivity)
    }

    private fun loadPrevScreen() {
        val intentLoadNextActivity = Intent(this, thursdayActivity::class.java)
        startActivity(intentLoadNextActivity)
    }

    private fun loadHomeScreen(){
        val intentLoadNextActivity = Intent(this, mainActivity::class.java)
        startActivity(intentLoadNextActivity)
    }

}
