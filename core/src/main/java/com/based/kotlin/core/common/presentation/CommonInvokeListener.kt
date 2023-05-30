package com.based.kotlin.core.common.presentation

interface CommonInvokeListener {
    fun continueProcess(): Boolean
    fun setRefreshTokenResultListener(listener: CommonListener)
    fun setCanRecallAPI(value: Boolean)
    fun setNullListener()
}