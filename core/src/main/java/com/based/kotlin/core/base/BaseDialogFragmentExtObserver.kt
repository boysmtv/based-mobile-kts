package com.based.kotlin.core.base

import androidx.lifecycle.LiveData
import com.based.kotlin.core.common.entity.Result

fun <A> BaseDialogFragment<*>.observeDataFlow(
    liveData: LiveData<out Result<A>>,
    onLoadState: (() -> Unit)? = null,
    onErrorState: ((Result<A>) -> Unit)? = null,
    onSuccessState: ((A) -> Unit)? = null
) {
    liveData.observe(viewLifecycleOwner) { result ->
        result?.let {
            when (result.status) {
                Result.Status.LOADING -> onLoadForDataFlow(result, onLoadState)
                Result.Status.SUCCESS -> onSuccessForDataFlow(result, onSuccessState)
                Result.Status.ERROR -> onErrorForDataFlow(result, onErrorState)
            }
        }
    }
}

fun <A> BaseDialogFragment<*>.onLoadForDataFlow(
    resultState: Result<A>,
    onLoadState: (() -> Unit)? = null
) {
    resultState.getLoadingStateIfNotHandled()?.let {
        dialogInitCustomOnLoadState?.let { it() }

        onLoadState?.let { it() }
    }
}

fun <A> BaseDialogFragment<*>.onSuccessForDataFlow(
    resultState: Result<A>,
    onSuccessState: ((A) -> Unit)? = null
) {
    resultState.getSuccessStateIfNotHandled()?.let { data ->
        onSuccessState?.let { it(data) }

        dialogInitCustomOnSuccessState?.let { it() }
    }
}

fun <A> BaseDialogFragment<*>.onErrorForDataFlow(
    resultState: Result<A>,
    onErrorState: ((Result<A>) -> Unit)? = null
) {
    resultState.getErrorStateIfNotHandled()?.let {
        dialogInitCustomOnErrorState?.let { it() }
        onErrorState?.invoke(resultState)
    }
}