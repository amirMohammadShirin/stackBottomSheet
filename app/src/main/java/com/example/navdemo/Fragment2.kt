package com.example.navdemo

import android.os.Bundle
import android.view.View
import android.widget.TextView

/**
 * Created by aShirin on 1/27/2024.
 */
class Fragment2 : CustomFragment(R.layout.fragment_2) {

    init {
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentView.findViewById<TextView>(R.id.txtFragment2).setOnClickListener {
            close()
        }
    }

    private fun Fragment2.close() {
        fragmentManager?.popBackStack()
        (fragmentManager?.fragments?.last() as? CustomFragment)?.increaseSize()
    }

}