package com.example.navdemo

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheet1(val dialogHeight: Int) : BottomSheetDialogFragment() {

    var btBehavior: BottomSheetBehavior<FrameLayout>? = null
    var test: (() -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_n1, container, false)

    companion object {
        const val TAG = "BottomSheet1"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            (it as BottomSheetDialog).setupFullHeight()
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txtNext)?.setOnClickListener {
            test?.invoke()
        }
        view.findViewById<TextView>(R.id.txtBack)?.setOnClickListener {
            exit()
        }
    }


    private fun BottomSheetDialog.setupFullHeight() {
        btBehavior = (this as? BottomSheetDialog)?.behavior
        btBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == STATE_HIDDEN) {
                    btBehavior?.state = STATE_HALF_EXPANDED
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })
        val view = findViewById<ConstraintLayout>(R.id.root)
        view?.updateLayoutParams {
            height = (dialogHeight * 3) / 4
        }
        btBehavior?.state = STATE_EXPANDED
    }

    fun enter() {
        val translation = PropertyValuesHolder.ofFloat("translationY", 120f)
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

}