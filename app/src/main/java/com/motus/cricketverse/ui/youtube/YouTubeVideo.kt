package com.motus.cricketverse.ui.youtube

/** Simple model for YouTube search result */
data class YouTubeVideo(
    val title: String,
    val thumbnailUrl: String,
    val videoId: String,
    val channelName: String
)
