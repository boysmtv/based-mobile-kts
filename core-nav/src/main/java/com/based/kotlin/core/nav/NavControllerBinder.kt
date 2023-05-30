package com.based.kotlin.core.nav

import androidx.navigation.NavController
import javax.inject.Inject

class NavControllerBinder @Inject constructor() {

    var navController: NavController? = null

    var needReset: Boolean = false

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }
}