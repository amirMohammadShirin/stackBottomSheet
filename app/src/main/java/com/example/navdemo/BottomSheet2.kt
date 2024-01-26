package com.example.navdemo

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheet2(val dialogHeight: Int) : BottomSheetDialogFragment() {

    lateinit var btView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_n2, container, false).also {
        btView = it
    }

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
        view.findViewById<TextView>(R.id.txtContent)?.setOnClickListener {
            animate(20f)
        }
    }


    private fun BottomSheetDialog.setupFullHeight() {
        val behaviour = (this as? BottomSheetDialog)?.behavior
        behaviour?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == STATE_HIDDEN) {
                    behaviour.state = STATE_HALF_EXPANDED
                }
                println("SWEET state ${newState}")
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                println("SWEET slide ${slideOffset}")
            }

        })
        val view = findViewById<ConstraintLayout>(R.id.root)
        view?.updateLayoutParams {
            height = (dialogHeight * 3) / 4
        }
        behaviour?.state = STATE_EXPANDED
    }

    fun animate(translationY: Float) {
        with(btView) {
            this.animate()?.translationY(translationY)?.scaleY(0.9f)?.scaleX(0.9f)
                ?.setDuration(100)
                ?.start()
        }
    }


}