package com.motus.cricketverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast
import com.motus.cricketverse.adapter.ScoreAdapter
import com.motus.cricketverse.model.LiveScore
import com.motus.cricketverse.network.CricApiService
import com.motus.cricketverse.repository.MatchRepository
import com.motus.cricketverse.network.RetrofitClient
import com.motus.cricketverse.viewmodel.ScoreViewModel
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class ScoreFragment : Fragment() {
    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var scoreAdapter: ScoreAdapter
    private lateinit var viewModel: ScoreViewModel
    private lateinit var scoreTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scores, container, false)
        scoreTextView = view.findViewById(R.id.tvLiveScore)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Animation
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.startAnimation(animation)

        // RecyclerView setup
        scoreAdapter = ScoreAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = scoreAdapter

        // Setup API & Repository
        val apiService = RetrofitClient.getRetrofitInstance().create(CricApiService::class.java)
        val repository = MatchRepository(apiService)

        // ViewModel setup
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ScoreViewModel(repository) as T
            }
        })[ScoreViewModel::class.java]

        // Observe Live Score
        viewModel.liveScore.observe(viewLifecycleOwner) { liveScore ->
            liveScore?.let {
                updateUI(it)
            }
        }

        // Observe Errors
        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        // Fetch Score (use your actual API key)
        viewModel.fetchLiveScore(apiKey = "a603d924-a1b3-4c3b-98fa-37a320bf4596")
    }

    private fun updateUI(score: LiveScore) {
        data class LiveScore(
            val id: String?,
            val name: String?,
            val status: String?,
            val venue: String?,
            val date: String?
        )
    }
}