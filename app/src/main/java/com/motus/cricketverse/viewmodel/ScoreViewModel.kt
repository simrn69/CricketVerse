package com.motus.cricketverse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motus.cricketverse.model.LiveScore
import com.motus.cricketverse.repository.MatchRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ScoreViewModel(private val repository: MatchRepository) : ViewModel() {

    private val _liveScore = MutableLiveData<LiveScore>()
    val liveScore: LiveData<LiveScore> = _liveScore

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchLiveScore(apiKey: String) {
        viewModelScope.launch {
            try {
                val response: Response<LiveScore> = repository.getLiveScores(apiKey)
                if (response.isSuccessful && response.body() != null) {
                    _liveScore.postValue(response.body())
                } else {
                    _error.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _error.postValue("Exception: ${e.localizedMessage}")
            }
        }
    }
}