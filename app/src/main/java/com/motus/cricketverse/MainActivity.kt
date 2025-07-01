package com.motus.cricketverse
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.motus.cricketverse.ScoreFragment
import com.motus.cricketverse.GamesFragment
import com.motus.cricketverse.MapFragment
import com.motus.cricketverse.AnalyticsFragment
import com.motus.cricketverse.ui.HomeFragment
import com.motus.cricketverse.ui.QuizFragment
import com.motus.cricketverse.ui.youtube.YouTubeFragment
import com.motus.cricketverse.ui.kismet.KismetFragment
import com.motus.cricketverse.ui.fantasy.FantasyFragment
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        loadFragment(HomeFragment()) // Default tab

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_scores -> ScoreFragment()
                R.id.nav_map -> MapFragment()
                R.id.nav_quiz -> QuizFragment()
                R.id.nav_games -> GamesFragment()
                R.id.nav_analytics -> AnalyticsFragment()
                R.id.nav_youtube -> YouTubeFragment()
                R.id.nav_kismet -> KismetFragment()
                R.id.nav_fantasy -> FantasyFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}