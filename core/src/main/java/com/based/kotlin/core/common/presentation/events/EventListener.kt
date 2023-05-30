package com.based.kotlin.core.common.presentation.events

import android.app.Activity
import com.based.kotlin.core.common.presentation.CommonInvokeListener
import java.util.concurrent.atomic.AtomicBoolean

interface EventListener {
    var common: CommonInvokeListener
    val isSessionIdle: AtomicBoolean
    fun bindActivity(activity: Activity)
}