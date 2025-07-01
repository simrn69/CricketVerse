package com.motus.cricketverse.ui.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.motus.cricketverse.R

class YouTubeAdapter(
    private var videos: List<YouTubeVideo>,
    private val onClick: (YouTubeVideo) -> Unit
) : RecyclerView.Adapter<YouTubeAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_youtube_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)
        holder.itemView.setOnClickListener { onClick(video) }
    }

    override fun getItemCount(): Int = videos.size

    fun update(list: List<YouTubeVideo>) {
        videos = list
        notifyDataSetChanged()
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.titleTextView)
        private val channel: TextView = itemView.findViewById(R.id.channelTextView)
        private val thumb: ImageView = itemView.findViewById(R.id.thumbnailImageView)

        fun bind(video: YouTubeVideo) {
            title.text = video.title
            channel.text = video.channelName
            Glide.with(itemView).load(video.thumbnailUrl).into(thumb)
        }
    }
}
