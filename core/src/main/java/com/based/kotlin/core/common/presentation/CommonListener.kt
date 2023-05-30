package com.based.kotlin.core.common.presentation

interface CommonListener {
    fun restartTask()
    fun mainErrorHandler(
        code: String?,
        title: String? = null,
        message: String? = null,
        httpCode: String? = null
    )

    fun showProgressDialog(isShow: Boolean)
}