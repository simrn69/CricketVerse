package com.motus.cricketverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.motus.cricketverse.adapter.MatchAdapter
import com.motus.cricketverse.databinding.FragmentMatchesBinding
import com.motus.cricketverse.viewmodel.MatchViewModel

class MatchesFragment : Fragment() {

    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    private lateinit var matchViewModel: MatchViewModel
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.startAnimation(animation)

        matchAdapter = MatchAdapter(emptyList())
        binding.matchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.matchRecyclerView.adapter = matchAdapter

        matchViewModel = ViewModelProvider(this)[MatchViewModel::class.java]
        matchViewModel.getMatches().observe(viewLifecycleOwner) { matches ->
            matchAdapter.updateData(matches)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
