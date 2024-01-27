package com.example.navdemo

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED

/**
 * Created by aShirin on 1/27/2024.
 */
open class CustomFragment(
    private val layout: Int
) : Fragment() {

    protected var state: CustomFragmentState = CustomFragmentState.HIDDEN
    protected lateinit var fragmentView: View
    protected var bottomSheet: FrameLayout? = null
    protected var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(layout, container, false).also {
            fragmentView = it
            try {
                bottomSheet = fragmentView.findViewById<FrameLayout>(R.id.bottomSheetFrame)
                bottomSheet?.let { frame ->
                    bottomSheetBehavior = BottomSheetBehavior.from(frame)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        state = CustomFragmentState.ENTER
        bottomSheetBehavior?.setBottomSheetCallback(defaultCallback)
        bottomSheetBehavior?.state = STATE_EXPANDED
    }

    private val defaultCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                bottomSheetBehavior?.state = STATE_EXPANDED
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        bottomSheetBehavior?.removeBottomSheetCallback(defaultCallback)
    }

    fun navigate(destination: Fragment) {
        decreaseSize()
        state = CustomFragmentState.REENTER
        fragmentManager?.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                R.anim.smoother_slide_up_in,
                R.anim.smoother_slide_down_out
            )
            addToBackStack(destination::class.java.simpleName)
            add(R.id.nav_host_fragment, destination, destination::class.java.simpleName)
        }

    }

    fun increaseSize() {
        val translation = PropertyValuesHolder.ofFloat("translationY", 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f)
        ObjectAnimator.ofPropertyValuesHolder(bottomSheet, translation, scaleX, scaleY).apply {
            duration = 300
            start()
        }

    }

    fun decreaseSize() {
        val translation = PropertyValuesHolder.ofFloat("translationY", 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.9f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.9f)
        ObjectAnimator.ofPropertyValuesHolder(bottomSheet, translation, scaleX, scaleY).apply {
            duration = 300
            start()
        }
    }

    override fun onResume() {
        super.onResume()
        if (state == CustomFragmentState.REENTER)
            increaseSize()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }
}

sealed class CustomFragmentState {
    data object HIDDEN : CustomFragmentState()
    data object ENTER : CustomFragmentState()
    data object REENTER : CustomFragmentState()

}
