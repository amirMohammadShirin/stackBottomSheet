package com.example.navdemo.test

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.NOT_FOCUSABLE
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navdemo.CustomFragmentState
import com.example.navdemo.R
import com.example.navdemo.navigate
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Created by aShirin on 1/27/2024.
 */
class TestFragment1 : Fragment() {

    private val TAG = "TestFragment1"

    private lateinit var firstBottomSheet: LinearLayout
    private lateinit var secondBottomSheet: LinearLayout

    private var firstBehavior: BottomSheetBehavior<LinearLayout>? = null
    private var secondBehavior: BottomSheetBehavior<LinearLayout>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context).inflate(R.layout.test1, container, false).also {
        println("$TAG onCreateView")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstBottomSheet =
            view.findViewById<LinearLayout>(R.id.firstBottomSheet)
        firstBehavior = BottomSheetBehavior.from(firstBottomSheet)

        secondBottomSheet =
            view.findViewById<LinearLayout>(R.id.secondBottomSheet)
        secondBehavior = BottomSheetBehavior.from(secondBottomSheet)

        firstBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        firstBehavior?.isDraggable = true

        secondBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        secondBehavior?.isDraggable = true

        secondBehavior?.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == STATE_EXPANDED)
                    increaseSize()
            }
        })

        firstBehavior?.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {


            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == STATE_EXPANDED)
                    decreaseSize()
                if (newState == STATE_HIDDEN)
                    increaseSize()

            }
        })

        view.findViewById<TextView>(R.id.txtS1).setOnClickListener {
            firstBehavior?.state = STATE_EXPANDED
        }
        view.findViewById<TextView>(R.id.txtS2).setOnClickListener {
        }
        view.findViewById<View>(R.id.rootView).setOnClickListener {
            firstBehavior?.state = STATE_HIDDEN
        }
    }

    fun increaseSize() {
        val translation = PropertyValuesHolder.ofFloat("translationY", 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f)
        val view = view?.findViewById<View>(R.id.lnr1)
        ObjectAnimator.ofPropertyValuesHolder(view, translation, scaleX, scaleY).apply {
            duration = 300
            start()
        }

    }

    fun decreaseSize() {
        val translation = PropertyValuesHolder.ofFloat("translationY", 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.9f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.9f)
        val view = view?.findViewById<View>(R.id.lnr1)
        ObjectAnimator.ofPropertyValuesHolder(view, translation, scaleX, scaleY).apply {
            duration = 300
            start()
        }.doOnEnd {
            firstBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            secondBottomSheet.isEnabled = false
            secondBottomSheet.isClickable = false
            secondBottomSheet.children.forEach {
                it.isEnabled = false
                it.isClickable = false
            }
        }
    }
}