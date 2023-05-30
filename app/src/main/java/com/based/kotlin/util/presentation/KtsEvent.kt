package com.based.kotlin.util.presentation

import android.app.Activity
import com.based.kotlin.activity.MainActivity
import com.based.kotlin.core.common.presentation.CommonInvokeListener
import com.based.kotlin.core.common.presentation.events.EventListener
import com.based.kotlin.util.presentation.events.CommonEvent
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtsEvent @Inject constructor() : EventListener {
    private lateinit var activity: MainActivity

    override lateinit var common: CommonInvokeListener

    override val isSessionIdle = AtomicBoolean(false)

    override fun bindActivity(activity: Activity) {
        this.activity = activity as MainActivity
        this.common = CommonEvent(activity)
    }
}