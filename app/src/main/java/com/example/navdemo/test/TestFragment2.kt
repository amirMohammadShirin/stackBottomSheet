package com.example.navdemo.test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navdemo.R

/**
 * Created by aShirin on 1/27/2024.
 */
class TestFragment2 : Fragment() {

    private val TAG = "TestFragment2"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context).inflate(R.layout.test2, container, false).also {
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
        println("$TAG onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("$TAG onViewStateRestored")
    }


}