package com.based.kotlin.core.common.navigation

import com.based.kotlin.core.R

sealed class TransitionType {
    abstract val enterTransition: Int
    abstract val exitTransition: Int

    object EnterMainNavigationTransition : TransitionType() {
        override val enterTransition: Int = R.anim.enter_from_right
        override val exitTransition: Int = R.anim.enter_from_left
    }

    object ExitMainNavigationTransition : TransitionType() {
        override val enterTransition: Int = R.anim.enter_from_right
        override val exitTransition: Int = R.anim.exit_to_left
    }

    object SheetNavigationTransition : TransitionType() {
        override val enterTransition: Int = R.anim.slide_in_up
        override val exitTransition: Int = R.anim.slide_out_down
    }

    object Stay : TransitionType() {
        override val enterTransition: Int = R.anim.stay
        override val exitTransition: Int = R.anim.stay
    }

    data class Custom(override val enterTransition: Int, override val exitTransition: Int) : TransitionType()
}