package com.example.orderfood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.orderfood.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavBar: BottomNavigationView
    private lateinit var frameContainer: FrameLayout
    private lateinit var frag: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavBar = findViewById(R.id.nav_bar)
        frameContainer = findViewById(R.id.frame_container)
        var fragmentManager = supportFragmentManager
        var fragmentTran = fragmentManager.beginTransaction()
        frag = RecipeFragment()
        fragmentTran.replace(R.id.frame_container, frag)
        fragmentTran.commit()
        bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.recipes_menu -> {
                    frag = RecipeFragment()
                    fragmentTran = fragmentManager.beginTransaction()
                    fragmentTran.replace(R.id.frame_container, frag).commit()
                }
                R.id.cart_menu -> {
                    frag = CartFragment()
                    fragmentTran = fragmentManager.beginTransaction()
                    fragmentTran.replace(R.id.frame_container, frag).commit()
                }
                R.id.jokes_menu -> {
                    frag = JokesFragment()
                    fragmentTran = fragmentManager.beginTransaction()
                    fragmentTran.replace(R.id.frame_container, frag).commit()
                }
            }
            true
        }
    }
}