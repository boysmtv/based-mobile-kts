package com.based.kotlin.core.util

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.based.kotlin.core.base.contract.SheetFactory
import com.based.kotlin.core.base.contract.SheetFragment
import com.based.kotlin.core.common.navigation.TransitionType
import com.based.kotlin.utilities.constants.Constants.ZERO
import com.mandiri.bds.core.base.contract.ResultCallback

class Host private constructor(private val fragmentManager: FragmentManager) {

    private var fragmentId: Int = ZERO

    constructor(activity: FragmentActivity) : this(activity.supportFragmentManager)

    fun show(
        sheetFragment: SheetFragment<Unit>,
        fragmentId: Int = android.R.id.content
    ): Host {
        this.fragmentId = fragmentId
        createFragment(sheetFragment, fragmentManager)
        return this
    }

    fun <Result : Any> show(
        sheetFragment: SheetFragment<Result>,
        transition: Pair<TransitionType, TransitionType>? = null,
        fragmentId: Int = android.R.id.content,
        callback: (() -> ResultCallback<Result>)
    ): Host {
        this.fragmentId = fragmentId
        createFragment(sheetFragment, fragmentManager, transition, callback)
        return this
    }

    private fun createFragment(
        sheetFragment: SheetFragment<Unit>,
        fragmentManager: FragmentManager,
    ) {
        sheetFragment.apply {
            isFragmentWithResultCallback = false
        }
        with(sheetFragment) {
            fragmentManager.commit {
                setCustomAnimations(enterAnimation.enterTransition, enterAnimation.exitTransition)
                add(fragmentId, fragment, fragment.tag)
            }
        }
    }

    private fun <Result : Any> createFragment(
        sheetFragment: SheetFragment<Result>,
        fragmentManager: FragmentManager,
        transition: Pair<TransitionType, TransitionType>? = null,
        fragmentCallback: (() -> ResultCallback<Result>)
    ) {
        sheetFragment.apply {
            resultCallback = fragmentCallback.invoke()
            transition?.let {
                enterAnimation = it.first
                exitAnimation = it.second
            }
            isFragmentWithResultCallback = true
        }
        with(sheetFragment) {
            fragmentManager.commit {
                setCustomAnimations(enterAnimation.enterTransition, enterAnimation.exitTransition)
                add(fragmentId, fragment, fragment.tag)
            }
        }
    }

    fun <Input : Any, Result : Any> show(
        input: Input,
        sheetFactory: SheetFactory<Input, Result>,
        transition: Pair<TransitionType, TransitionType>? = null,
        fragmentId: Int = android.R.id.content,
        callback: (() -> ResultCallback<Result>)
    ): Host {
        this.fragmentId = fragmentId
        createFragment(input, sheetFactory, fragmentManager, transition, callback)
        return this
    }

    private fun <Input : Any, Result : Any> createFragment(
        input: Input,
        sheetFactory: SheetFactory<Input, Result>,
        fragmentManager: FragmentManager,
        enterTransition: Pair<TransitionType, TransitionType>? = null,
        fragmentCallback: (() -> ResultCallback<Result>)
    ) {
        sheetFactory.apply {
            resultCallback = fragmentCallback.invoke()
            enterTransition?.let {
                enterAnimation = it.first
                exitAnimation = it.second
            }
            isFragmentWithResultCallback = true
            model = input
        }
        with(sheetFactory) {
            fragmentManager.commit {
                setCustomAnimations(enterAnimation.enterTransition, enterAnimation.exitTransition)
                add(fragmentId, fragment, fragment.tag)
            }
        }
    }

    fun <Input : Any, Result : Any> show(
        input: Input,
        sheetFactory: SheetFactory<Input, Result>,
        transition: Pair<TransitionType, TransitionType>? = null,
        fragmentId: Int = android.R.id.content
    ): Host {
        this.fragmentId = fragmentId
        createFragment(input, sheetFactory, fragmentManager, transition)
        return this
    }

    private fun <Input : Any, Result : Any> createFragment(
        input: Input,
        sheetFactory: SheetFactory<Input, Result>,
        fragmentManager: FragmentManager,
        enterTransition: Pair<TransitionType, TransitionType>? = null
    ) {
        sheetFactory.apply {
            enterTransition?.let {
                enterAnimation = it.first
                exitAnimation = it.second
            }
            isFragmentWithResultCallback = true
            model = input
        }
        with(sheetFactory) {
            fragmentManager.commit {
                setCustomAnimations(enterAnimation.enterTransition, enterAnimation.exitTransition)
                add(fragmentId, fragment, fragment.tag)
            }
        }
    }
}