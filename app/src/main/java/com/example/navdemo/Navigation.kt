package com.example.navdemo

import android.app.Activity
import androidx.navigation.findNavController
import androidx.navigation.navOptions

/**
 * Created by aShirin on 1/27/2024.
 */

val ENTER_ANIMATION: Int = R.anim.bottom_sheet_enter
val EXIT_ANIMATION: Int = R.anim.bottom_sheet_exit
val POP_ENTER: Int = R.anim.bottom_sheet_pop_enter

fun navigate(activity: Activity?, action: Int) {
    activity?.findNavController(R.id.nav_host_fragment)
        ?.navigate(action, null, navOptions {
            anim {
                enter = ENTER_ANIMATION
                exit = EXIT_ANIMATION
                popExit = EXIT_ANIMATION
                popEnter = ENTER_ANIMATION
            }
        })
}