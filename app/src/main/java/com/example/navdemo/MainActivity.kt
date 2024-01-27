package com.example.navdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class MainActivity() : AppCompatActivity() {

    lateinit var txtStart: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtStart = findViewById<TextView>(R.id.txtStart)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(Fragment1::class.java.simpleName)
            add(R.id.nav_host_fragment, Fragment1(), "Fragment1")
        }
        txtStart.setOnClickListener {
            navigate(R.id.action_fragment1_to_fragment2)
        }
    }


    fun navigate(action: Int) {
        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).navigate(action)
    }

    fun getHeight(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(
            displayMetrics
        )
        return displayMetrics.heightPixels ?: 1
    }

    override fun onBackPressed() {
        findNavController(R.id.nav_host_fragment).popBackStack()
    }

}