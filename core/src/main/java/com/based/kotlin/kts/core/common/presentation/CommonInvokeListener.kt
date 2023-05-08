package com.based.kotlin.kts.core.common.presentation

interface CommonInvokeListener {
    fun continueProcess(): Boolean
    fun invalidRefreshToken()
    fun setRefreshTokenResultListener(listener: CommonListener)
    fun setCanRecallAPI(value: Boolean)
    fun setNullListener()
}