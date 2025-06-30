package com.motus.cricketverse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.motus.cricketverse.R
import com.motus.cricketverse.model.Match

class MatchAdapter(private var matchList: List<Match>) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matchList[position]
        holder.matchName.text = match.name
        holder.matchStatus.text = match.status
        holder.matchVenue.text = match.venue
        holder.matchDate.text = match.date
    }

    override fun getItemCount(): Int = matchList.size

    fun updateData(newMatchList: List<Match>) {
        matchList = newMatchList
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchName: TextView = itemView.findViewById(R.id.matchName)
        val matchStatus: TextView = itemView.findViewById(R.id.matchStatus)
        val matchVenue: TextView = itemView.findViewById(R.id.matchVenue)
        val matchDate: TextView = itemView.findViewById(R.id.matchDate)
    }
}