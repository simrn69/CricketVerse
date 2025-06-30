package com.motus.cricketverse

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.motus.cricketverse.adapter.MatchAdapter
import com.motus.cricketverse.viewmodel.MatchViewModel

class MatchesFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.startAnimation(animation)
    }
    private lateinit var matchViewModel: MatchViewModel
    private lateinit var matchRecyclerView: RecyclerView
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_matches, container, false)

        matchRecyclerView = view.findViewById(R.id.matchRecyclerView)
        matchRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        matchAdapter = MatchAdapter(emptyList())
        matchRecyclerView.adapter = matchAdapter

        matchViewModel = ViewModelProvider(this)[MatchViewModel::class.java]

        matchViewModel.getMatches().observe(viewLifecycleOwner) { matches ->
            matchAdapter.updateData(matches)
        }

        return view
    }
}