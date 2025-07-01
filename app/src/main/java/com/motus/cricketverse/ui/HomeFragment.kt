package com.motus.cricketverse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.motus.cricketverse.adapter.MatchAdapter
import com.motus.cricketverse.databinding.FragmentHomeBinding
import com.motus.cricketverse.viewmodel.MatchViewModel
import com.motus.cricketverse.games.TapGameActivity
import com.motus.cricketverse.games.TicTacToeActivity
import com.motus.cricketverse.ui.QuizFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var matchViewModel: MatchViewModel
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchAdapter = MatchAdapter(emptyList())
        binding.liveScoreList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.liveScoreList.adapter = matchAdapter

        matchViewModel = ViewModelProvider(this)[MatchViewModel::class.java]
        matchViewModel.getMatches().observe(viewLifecycleOwner) { matches ->
            matchAdapter.updateData(matches.take(3))
        }

        binding.quizButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, QuizFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.tapCricketButton.setOnClickListener {
            startActivity(Intent(requireContext(), TapGameActivity::class.java))
        }

        binding.cricketSnakeButton.setOnClickListener {
            startActivity(Intent(requireContext(), TicTacToeActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
