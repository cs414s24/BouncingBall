package com.example.bouncingball


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Speed is represented as a duration of sleep amount, where 50 means sleeping for 50 milliseconds.
    // Lowering the value increases the speed of the ball's movement
    private var speed = 50L // L means long type

    private var isRunning = false

    lateinit var ballView: BouncingBallView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get a reference to our ball
        ballView = findViewById(R.id.bouncingBallView)

    }

    fun startBouncingClick(view: View) {
        if (isRunning) {
            Toast.makeText(this, "it has already started", Toast.LENGTH_SHORT).show()
            return
        }

        isRunning = true
        // Keep calling move function in loop, which is run in a Thread
        Thread {
            while (true) {
                if(!isRunning) {
                    return@Thread
                }
                ballView.move()
                Thread.sleep(speed)
            }
        }.start()
    }

    fun stopBouncingClick(view: View) {
        isRunning = false
    }

    fun resetButton(view: View) {
        ballView.reset()
    }


    fun increaseSpeed(view: View) {
        // Lowering the value increases the speed of the ball's movement
        if (speed > 10) {
            speed -= 10
        } else{
            Toast.makeText(this, "It is fast enough!", Toast.LENGTH_SHORT).show()
        }
    }

    fun decreaseSpeed(view: View) {
        // increasing the value decrease the speed of the ball's movement
        speed += 10
    }
}