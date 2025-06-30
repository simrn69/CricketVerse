package com.motus.cricketverse.network

import com.motus.cricketverse.model.LiveScore
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CricApiService {
    @GET("cricScoreAPI") // replace with your real endpoint
    suspend fun getLiveScores(@Query("apikey") apiKey: String): Response<LiveScore>
}