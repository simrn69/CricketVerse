package com.motus.cricketverse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.motus.cricketverse.model.Match
import com.motus.cricketverse.network.CricApiService
import com.motus.cricketverse.network.RetrofitClient
import com.motus.cricketverse.repository.MatchRepository

class MatchViewModel : ViewModel() {

    private val apiService: CricApiService = RetrofitClient.getRetrofitInstance().create(CricApiService::class.java)
    private val matchRepository = MatchRepository(apiService)
    private val matchData = MutableLiveData<List<Match>>()

    fun getMatches(): LiveData<List<Match>> {
        matchRepository.fetchMatches("a603d924-a1b3-4c3b-98fa-37a320bf4596").observeForever { liveScores ->
            val matchList = liveScores.map { liveScore ->
                Match(
                    id = liveScore.id ?: "N/A",
                    name = liveScore.name ?: "Unknown Match",
                    status = liveScore.status ?: "Unknown",
                    venue = liveScore.venue ?: "TBD",
                    date = liveScore.date ?: "TBD"
                )
            }
            matchData.postValue(matchList)
        }
        return matchData
    }
}