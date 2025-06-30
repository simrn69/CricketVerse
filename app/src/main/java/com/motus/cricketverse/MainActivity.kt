package com.motus.cricketverse
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.motus.cricketverse.ScoreFragment
import com.motus.cricketverse.GamesFragment
import com.motus.cricketverse.QuizFragment
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        loadFragment(MatchesFragment()) // Default tab

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_matches -> MatchesFragment()
                R.id.nav_scores -> ScoreFragment()
                R.id.nav_games -> GamesFragment()
                R.id.nav_quiz -> QuizFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> MatchesFragment()
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