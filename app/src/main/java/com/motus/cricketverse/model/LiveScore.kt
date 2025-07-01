package com.motus.cricketverse.model

data class LiveScore(
    val id: String?,
    val teamName: String,
    val score: String,
    val overs: String,
    val wickets: String,
    val name: String?,
    val status: String?,
    val venue: String?,
    val date: String?
)