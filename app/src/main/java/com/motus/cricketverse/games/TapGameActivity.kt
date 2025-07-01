package com.motus.cricketverse.games

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.motus.cricketverse.R

class TapGameActivity : AppCompatActivity() {

    private lateinit var tapButton: Button
    private lateinit var scoreText: TextView
    private var score = 0
    private val handler = Handler(Looper.getMainLooper())
    private var timeLeft = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tap_game)

        tapButton = findViewById(R.id.tapButton)
        scoreText = findViewById(R.id.scoreText)

        tapButton.setOnClickListener {
            score++
            scoreText.text = getString(R.string.score_value, score, timeLeft)
        }
        startTimer()
    }

    private fun startTimer() {
        handler.post(object : Runnable {
            override fun run() {
                if (timeLeft > 0) {
                    timeLeft--
                    scoreText.text = getString(R.string.score_value, score, timeLeft)
                    handler.postDelayed(this, 1000)
                } else {
                    tapButton.isEnabled = false
                }
            }
        })
    }
}

