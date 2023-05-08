package com.based.kotlin.kts.core.common.presentation

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