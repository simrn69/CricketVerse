package com.motus.cricketverse

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.motus.cricketverse.games.TapGameActivity
import com.motus.cricketverse.games.TicTacToeActivity

class GamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)

        view.findViewById<Button>(R.id.btnTapGame).setOnClickListener {
            startActivity(Intent(requireContext(), TapGameActivity::class.java))
        }
        view.findViewById<Button>(R.id.btnTicTacToe).setOnClickListener {
            startActivity(Intent(requireContext(), TicTacToeActivity::class.java))
        }
        return view
    }
}

