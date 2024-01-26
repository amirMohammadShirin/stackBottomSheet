package com.example.navdemo

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class CustomBottomSheet : BottomSheetDialogFragment() {

    var heightSubtraction = 3
    var btBehavior: BottomSheetBehavior<FrameLayout>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            setupFullHeight()
        }
        return dialog
    }

    fun enter() {
        val translation = PropertyValuesHolder.ofFloat("translationY", 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.95f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.95f)
        ObjectAnimator.ofPropertyValuesHolder(view, translation, scaleX, scaleY).apply {
            duration = 300
            start()
        }
    }

    fun exit() {
        val translation = PropertyValuesHolder.ofFloat("translationY", 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f)
        ObjectAnimator.ofPropertyValuesHolder(view, translation, scaleX, scaleY).apply {
            duration = 300
            start()
        }

    }

    private fun setupFullHeight() {
        btBehavior = (this as? BottomSheetDialog)?.behavior
        btBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    btBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })
        val view = this.dialog?.findViewById<ConstraintLayout>(R.id.root)
        view?.updateLayoutParams {
            height = (getHeight() * 3) / 4
        }
        btBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun getHeight(): Int {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(
            displayMetrics
        )
        return displayMetrics.heightPixels ?: 1
    }

}