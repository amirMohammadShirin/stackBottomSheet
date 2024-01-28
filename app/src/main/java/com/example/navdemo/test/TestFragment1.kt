package com.example.navdemo.test

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.navdemo.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN

/**
 * Created by aShirin on 1/27/2024.
 */
class TestFragment1 : Fragment() {

    private val TAG = "TestFragment1"

    private var firstBottomSheet: LinearLayout? = null
    private var secondBottomSheet: LinearLayout? = null

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

        initFirstBottomSheet()

        initSecondBottomSheet()

        firstBehavior.updateState(STATE_HIDDEN)
        secondBehavior.updateState(STATE_EXPANDED)

        view.findViewById<TextView>(R.id.txtS1).setOnClickListener {
            firstBehavior.updateState(STATE_EXPANDED)
        }
        view.findViewById<TextView>(R.id.txtS2).setOnClickListener {
        }
        view.findViewById<View>(R.id.rootView).setOnClickListener {
            firstBehavior.updateState(STATE_HIDDEN)
        }
    }

    private fun BottomSheetBehavior<*>?.updateState(newState: Int) {
        this?.let { it.state = newState }
    }

    private fun initSecondBottomSheet() {

        secondBottomSheet =
            view?.findViewById<LinearLayout>(R.id.secondBottomSheet)

        secondBottomSheet?.let {
            secondBehavior = BottomSheetBehavior.from(it)
        }

        secondBehavior?.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == STATE_EXPANDED)
                    increaseSize()
            }
        })
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
        }
    }

    private fun initFirstBottomSheet() {
        firstBottomSheet =
            view?.findViewById<LinearLayout>(R.id.firstBottomSheet)
        firstBottomSheet?.let {
            firstBehavior = BottomSheetBehavior.from(it)
        }
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
    }


}


class SweetBottomSheet(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    val behaviour = BottomSheetBehavior.from(this)


    val callback = object : BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {

        }
    }

    init {
        behaviour.addBottomSheetCallback(callback)
    }

    fun updateState(state: Int) {
        behaviour.state = state
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        behaviour.removeBottomSheetCallback(callback)
    }

}