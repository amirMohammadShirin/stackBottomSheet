package com.example.navdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class MainActivity() : AppCompatActivity() {

    lateinit var txtStart: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtStart = findViewById<TextView>(R.id.txtStart)

        txtStart.setOnClickListener {
            navigate(R.id.action_startFragment_to_bottomSheet1)
        }
    }

    fun navigate(action: Int) {
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    fun getHeight(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(
            displayMetrics
        )
        return displayMetrics.heightPixels ?: 1
    }
}