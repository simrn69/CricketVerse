package com.motus.cricketverse.ui.fantasy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.motus.cricketverse.R

class FantasyFragment : Fragment() {

    private lateinit var winnerField: EditText
    private lateinit var batsmanField: EditText
    private lateinit var bowlerField: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fantasy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        winnerField = view.findViewById(R.id.winnerField)
        batsmanField = view.findViewById(R.id.batsmanField)
        bowlerField = view.findViewById(R.id.bowlerField)

        view.findViewById<Button>(R.id.saveButton).setOnClickListener { savePredictions() }
        view.findViewById<Button>(R.id.editButton).setOnClickListener { loadPredictions() }
    }

    private fun prefs() = requireContext().getSharedPreferences("predictions", Context.MODE_PRIVATE)

    private fun savePredictions() {
        val model = PredictionModel(
            winnerField.text.toString(),
            batsmanField.text.toString(),
            bowlerField.text.toString()
        )
        prefs().edit().apply {
            putString("winner", model.matchWinner)
            putString("batsman", model.topBatsman)
            putString("bowler", model.topBowler)
            apply()
        }
        Toast.makeText(requireContext(), "Prediction Saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadPredictions() {
        winnerField.setText(prefs().getString("winner", ""))
        batsmanField.setText(prefs().getString("batsman", ""))
        bowlerField.setText(prefs().getString("bowler", ""))
    }
}
