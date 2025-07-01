package com.motus.cricketverse.ui.kismet

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.motus.cricketverse.R

class KismetFragment : Fragment() {

    private val tips = listOf(
        "Consistency beats talent.",
        "Don’t chase the ball, chase the moment.",
        "Train like you play."
    )
    private lateinit var quoteView: TextView
    private var tipIndex = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kismet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteView = view.findViewById(R.id.kismetQuote)
        view.findViewById<Button>(R.id.askButton).setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon.", Toast.LENGTH_SHORT).show()
        }
        rotateQuotes()
    }

    private fun rotateQuotes() {
        quoteView.text = tips[tipIndex]
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 800 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 800 }
        quoteView.startAnimation(fadeIn)
        handler.postDelayed({
            quoteView.startAnimation(fadeOut)
            tipIndex = (tipIndex + 1) % tips.size
            handler.postDelayed({ rotateQuotes() }, 800)
        }, 3000)
    }
}
