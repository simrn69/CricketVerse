package com.motus.cricketverse.ui.youtube

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.motus.cricketverse.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class YouTubeFragment : Fragment() {

    private lateinit var adapter: YouTubeAdapter
    private lateinit var shimmer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmer = view.findViewById(R.id.shimmerLayout)
        val recycler = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.videoRecyclerView)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = YouTubeAdapter(emptyList()) { openVideo(it.videoId) }
        recycler.adapter = adapter
        fetchVideos()
    }

    private fun openVideo(videoId: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
        startActivity(intent)
    }

    private fun fetchVideos() {
        shimmer.startShimmer()
        lifecycleScope.launch {
            val service = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YouTubeService::class.java)
            val response = withContext(Dispatchers.IO) {
                service.searchVideos(
                    query = "latest cricket highlights",
                    maxResults = 10,
                    apiKey = BuildConfig.YOUTUBE_API_KEY
                )
            }
            val videos = response.items.map {
                YouTubeVideo(
                    title = it.snippet.title,
                    thumbnailUrl = it.snippet.thumbnails.medium.url,
                    videoId = it.id.videoId,
                    channelName = it.snippet.channelTitle
                )
            }
            adapter.update(videos)
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
        }
    }

    interface YouTubeService {
        @GET("search")
        suspend fun searchVideos(
            @Query("part") part: String = "snippet",
            @Query("q") query: String,
            @Query("type") type: String = "video",
            @Query("maxResults") maxResults: Int,
            @Query("order") order: String = "date",
            @Query("key") apiKey: String
        ): YouTubeResponse
    }

    data class YouTubeResponse(val items: List<Item>) {
        data class Item(val id: Id, val snippet: Snippet)
        data class Id(val videoId: String)
        data class Snippet(
            val title: String,
            val channelTitle: String,
            val thumbnails: Thumbnails
        )
        data class Thumbnails(val medium: Thumbnail)
        data class Thumbnail(val url: String)
    }
}
