package com.based.kotlin.core.base

import androidx.lifecycle.LiveData
import com.based.kotlin.core.common.entity.Result
import com.based.kotlin.core.util.httpError
import com.based.kotlin.utilities.constants.Constants.ZERO
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_401

fun <A> BaseActivity<*>.observeDataFlow(
    liveData: LiveData<out Result<A>>,
    onLoad: (() -> Unit)? = null,
    onError: ((Result<A>) -> Unit)? = null,
    onSuccess: ((A) -> Unit)? = null
) {
    liveData.observe(this, { result ->
        result?.let {
            when (result.status) {
                Result.Status.LOADING -> onLoadForDataFlow(result, onLoad)
                Result.Status.SUCCESS -> onSuccessForDataFlow(result, onSuccess)
                Result.Status.ERROR -> onErrorForDataFlow(result, onError)
            }
        }
    })
}

fun <A> BaseActivity<*>.onLoadForDataFlow(
    result: Result<A>,
    onLoad: (() -> Unit)? = null
) {
    result.getLoadingStateIfNotHandled()?.let {
        initCustomOnLoadState?.let { it() }

        onLoad?.let { it() }
    }
}

fun <A> BaseActivity<*>.onSuccessForDataFlow(
    result: Result<A>,
    onSuccess: ((A) -> Unit)? = null
) {
    result.getSuccessStateIfNotHandled()?.let { data ->
        onSuccess?.let { it(data) }

        initCustomOnSuccessState?.let { it() }
    }
}

fun <A> BaseActivity<*>.onErrorForDataFlow(
    result: Result<A>,
    onError: ((Result<A>) -> Unit)? = null
) {
    result.getErrorStateIfNotHandled()?.let {
        initCustomOnErrorState?.let { it() }

        if (result.code == CODE_401) errorHandler(result.code, result.title, result.message)
        else {
            onError?.let { it(result) } ?: run {
                errorHandler(
                    result.code,
                    result.title,
                    result.message
                )
            }
        }
    }
}