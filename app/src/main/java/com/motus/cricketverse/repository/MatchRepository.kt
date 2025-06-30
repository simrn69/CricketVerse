package com.motus.cricketverse.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.motus.cricketverse.model.LiveScore
import com.motus.cricketverse.network.CricApiService
import retrofit2.Response

class MatchRepository(private val apiService: CricApiService) {
    suspend fun getLiveScores(apiKey: String): Response<LiveScore> {
        return apiService.getLiveScores(apiKey)
    }
    fun fetchMatches(apiKey: String): LiveData<List<LiveScore>> = liveData {
        try {
            val response = apiService.getLiveScores(apiKey) // suspend function
            if (response.isSuccessful && response.body() != null) {
                emit(listOf(response.body()!!))
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}