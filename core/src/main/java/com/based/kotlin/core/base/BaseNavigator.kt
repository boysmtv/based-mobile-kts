package com.based.kotlin.core.base

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.based.kotlin.core.R
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_CARD_NUMBER
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_ENTRY_POINT
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_LAST_STATEMENT_DATE
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_ORIGIN
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_SCENARIO
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_SELECTED_ID
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_SERVICE_CODE
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_TRANSITION_IN
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.utilities.constants.Constants.ONE

abstract class BaseNavigator {

    abstract var navController: NavController?

    private var origin = EMPTY_STRING
    private var selectedId = EMPTY_STRING
    private var entryPoint = EMPTY_STRING
    protected var serviceCode = EMPTY_STRING
    private var lastStatementDate = EMPTY_STRING
    private var scenario = EMPTY_STRING
    private var transitionIn = true
    private var cardNumber = EMPTY_STRING

    private val transitionInAnim = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)

    private val transitionOutAnim = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_left)
        .setExitAnim(R.anim.slide_out_right)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)

    fun pop(graphId: Int, inclusive: Boolean = true, nav: NavController? = null) {
        nav ?: (navController)?.popBackStack(graphId, inclusive)
    }

    fun navigate(
        graphId: Int,
        popBundle: Triple<Int?, Boolean, Boolean> = popStack(),
        nav: NavController? = null,
        bundle: Bundle? = null,
        isResetBundle: Boolean = false
    ) {
        if (isResetBundle) resetNavigationBundle()

        val defaultBundle = bundleOf(
            ARGS_KEY_ORIGIN to origin,
            ARGS_KEY_SCENARIO to scenario,
            ARGS_KEY_TRANSITION_IN to transitionIn,
            ARGS_KEY_SELECTED_ID to selectedId,
            ARGS_KEY_ENTRY_POINT to entryPoint,
            ARGS_KEY_SERVICE_CODE to serviceCode,
            ARGS_KEY_LAST_STATEMENT_DATE to lastStatementDate,
            ARGS_KEY_CARD_NUMBER to cardNumber
        )
        bundle?.let { defaultBundle.putAll(it) }
        val navigator = nav ?: navController
        navigator?.navigate(graphId, defaultBundle, transition(popBundle, transitionIn))
    }

    private fun resetNavigationBundle() {
        this.origin = EMPTY_STRING
        this.scenario = EMPTY_STRING
        this.transitionIn = true
        this.selectedId = EMPTY_STRING
    }

    private fun transition(
        popStack: Triple<Int?, Boolean, Boolean>,
        isTransitionIn: Boolean
    ) = popStack.first?.let {
        if (isTransitionIn) {
            transitionInAnim
        } else {
            transitionOutAnim
        }.apply {
            setPopUpTo(it, popStack.second)
            setLaunchSingleTop(popStack.third)
        }.build()
    } ?: run {
        (if (isTransitionIn) transitionInAnim else transitionOutAnim).build()
    }

    private fun popStack(
        graphId: Int? = null,
        inclusive: Boolean = true,
        launchSingleTop: Boolean = true
    ) = Triple(graphId, inclusive, launchSingleTop)

    interface BaseNavigatorInterface {
        fun setNavigationBundle(
            origin: String = EMPTY_STRING,
            scenario: String = EMPTY_STRING,
            transitionIn: Boolean = true,
            selectedId: String = ONE.toString()
        )

        fun backToPrevious()
    }
}