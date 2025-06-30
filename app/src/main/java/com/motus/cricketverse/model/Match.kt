package com.motus.cricketverse.model

data class Match(
    val id: String,
    val name: String,
    val status: String,
    val venue: String,
    val date: String
)