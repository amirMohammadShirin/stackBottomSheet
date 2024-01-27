package com.example.navdemo

import android.os.Bundle
import android.view.View
import android.widget.TextView

/**
 * Created by aShirin on 1/27/2024.
 */
class Fragment1 : CustomFragment(R.layout.fragment_1) {

    init {
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txtFragment1).setOnClickListener {
            navigate(Fragment2())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}