package com.example.navdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


class BottomSheet1() : CustomBottomSheet() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_n1, container, false)

    companion object {
        const val TAG = "BottomSheet1"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txtNext)?.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                nav()
            }, 180)
            enter()
        }
        view.findViewById<TextView>(R.id.txtBack)?.setOnClickListener {
            exit()
        }
    }

    fun nav() {
        findNavController().navigate(
            R.id.action_bottomSheet1_to_bottomSheet2,
            null,
            NavOptions.Builder().setPopUpTo(R.id.action_bottomSheet1_to_bottomSheet2, true).build()
        )
    }

}