package com.motus.cricketverse.core

import android.content.Context
import android.util.Log
import com.motus.cricketverse.R

object HealthCheck {
    fun run(context: Context) {
        val tag = "CricketVerse-Debug"
        val routes = listOf(
            R.id.nav_home to R.layout.fragment_home,
            R.id.nav_scores to R.layout.fragment_scores,
            R.id.nav_youtube to R.layout.fragment_youtube,
            R.id.nav_kismet to R.layout.fragment_kismet,
            R.id.nav_fantasy to R.layout.fragment_fantasy
        )
        routes.forEach { (menuId, layout) ->
            try {
                context.resources.getResourceName(menuId)
                context.resources.getResourceName(layout)
            } catch (e: Exception) {
                Log.d(tag, "Missing reference: ${e.message}")
            }
        }
    }
}
