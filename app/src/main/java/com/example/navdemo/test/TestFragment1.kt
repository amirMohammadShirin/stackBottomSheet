package com.example.navdemo.test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.navdemo.R
import com.example.navdemo.navigate

/**
 * Created by aShirin on 1/27/2024.
 */
class TestFragment1 : Fragment() {

    private val TAG = "TestFragment1"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context).inflate(R.layout.test1, container, false).also {
        println("$TAG onCreateView")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("$TAG onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("$TAG onAttach")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("$TAG onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("$TAG onDestroyView")
    }

    override fun onStart() {
        super.onStart()
        println("$TAG onStart")
    }

    override fun onPause() {
        super.onPause()
        println("$TAG onPause")
    }

    override fun onStop() {
        super.onStop()
        println("$TAG onStop")
    }

    override fun onResume() {
        super.onResume()
        println("$TAG onResume")
    }

    override fun onDetach() {
        super.onDetach()
        println("$TAG onDetach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txtTest1).setOnClickListener {
            navigate(activity, R.id.action_testFragment1_to_testFragment2)
        }
        println("$TAG onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("$TAG onViewStateRestored")
    }

}