package com.example.navdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity() : AppCompatActivity() {

    lateinit var btnRed: TextView
    lateinit var btnGreen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRed = findViewById<TextView>(R.id.btnRed)
        btnGreen = findViewById<TextView>(R.id.btnGreen)

        val bottomSheet1 = BottomSheet1(getWindowHeight())
        val bottomSheet2 = BottomSheet2(getWindowHeight() - (getWindowHeight() / 8))

        bottomSheet1.test = {
            bottomSheet1.enter()
            Handler(Looper.getMainLooper()).postDelayed({
                bottomSheet2.show(supportFragmentManager, BottomSheet1.TAG)
            }, 180)
        }

        btnRed.setOnClickListener {
            bottomSheet1.show(supportFragmentManager, BottomSheet1.TAG)
        }
        btnGreen.setOnClickListener {

        }
    }

    fun showBottomSheet1(cancelable: Boolean = true) {


    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(
            displayMetrics
        )
        return displayMetrics.heightPixels
    }

}