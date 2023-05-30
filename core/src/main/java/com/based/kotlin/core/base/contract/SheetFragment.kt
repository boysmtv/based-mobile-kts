package com.based.kotlin.core.base.contract

import androidx.fragment.app.Fragment
import com.based.kotlin.core.common.navigation.TransitionType
import com.mandiri.bds.core.base.contract.ResultCallback

interface SheetFragment<Result> {
    val fragment: Fragment
    var enterAnimation: TransitionType
    var exitAnimation: TransitionType
    var resultCallback: ResultCallback<Result>
    var isFragmentWithResultCallback: Boolean
    fun popBackStack() = Unit
}