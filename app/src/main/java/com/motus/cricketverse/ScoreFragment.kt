package com.motus.cricketverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.motus.cricketverse.adapter.ScoreAdapter
import com.motus.cricketverse.model.ScoreItem
import java.io.BufferedReader
import java.io.InputStreamReader

class ScoreFragment : Fragment() {

    private lateinit var scoreAdapter: ScoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.startAnimation(animation)

        scoreAdapter = ScoreAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = scoreAdapter

        loadLocalScores()
    }

    private fun loadLocalScores() {
        try {
            val inputStream = requireContext().assets.open("sample_scores.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val json = reader.readText()
            reader.close()

            val type = object : TypeToken<SampleScoreResponse>() {}.type
            val response: SampleScoreResponse = Gson().fromJson(json, type)
            val items = response.data.map {
                ScoreItem(
                    teamName = it.teamName,
                    score = it.score,
                    overs = it.overs,
                    wickets = it.wickets,
                    status = it.status
                )
            }
            scoreAdapter.submitList(items)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Failed to load scores", Toast.LENGTH_SHORT).show()
        }
    }

    data class SampleScoreResponse(
        val data: List<LocalScore>
    )

    data class LocalScore(
        val id: String,
        val name: String,
        val status: String,
        val venue: String,
        val date: String,
        val teamName: String,
        val score: String,
        val overs: String,
        val wickets: String
    )
}
