package com.gdpu.lostandfound.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.fragment.FoundFragment
import com.gdpu.lostandfound.fragment.LostFragment
import com.gdpu.lostandfound.fragment.MessageFragment
import com.gdpu.lostandfound.fragment.PersonalFragment

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment(LostFragment())
        bottomNav = findViewById(R.id.bottomNavigation) as BottomNavigationView
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.navigation_lost -> {
                    loadFragment(LostFragment())
                    return@setOnNavigationItemReselectedListener
                }

                R.id.navigation_found -> {
                    loadFragment(FoundFragment())
                    return@setOnNavigationItemReselectedListener
                }

                R.id.navigation_message -> {
                    loadFragment(MessageFragment())
                    return@setOnNavigationItemReselectedListener
                }

                R.id.navigation_personal -> {
                    loadFragment(PersonalFragment())
                    return@setOnNavigationItemReselectedListener
                }

            }

        }

    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}