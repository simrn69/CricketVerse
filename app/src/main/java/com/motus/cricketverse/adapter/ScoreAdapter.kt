package com.motus.cricketverse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.motus.cricketverse.R
import com.motus.cricketverse.model.ScoreItem

class ScoreAdapter : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    private var scoreList: List<ScoreItem> = emptyList()

    fun submitList(list: List<ScoreItem>) {
        scoreList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val item = scoreList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = scoreList.size

    class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName: TextView = itemView.findViewById(R.id.teamNameTextView)
        private val score: TextView = itemView.findViewById(R.id.scoreTextView)
        private val overs: TextView = itemView.findViewById(R.id.oversTextView)
        private val wickets: TextView = itemView.findViewById(R.id.wicketsTextView)
        private val status: TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(item: ScoreItem) {
            teamName.text = item.teamName
            score.text = item.score
            overs.text = "Overs: ${item.overs}"
            wickets.text = "Wickets: ${item.wickets}"
            status.text = item.status
        }
    }
}