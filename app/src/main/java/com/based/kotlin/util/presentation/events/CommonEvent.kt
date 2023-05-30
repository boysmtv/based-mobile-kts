package com.based.kotlin.util.presentation.events

import com.based.kotlin.R
import com.based.kotlin.activity.MainActivity
import com.based.kotlin.core.common.presentation.CommonInvokeListener
import com.based.kotlin.core.common.presentation.CommonListener
import org.threeten.bp.Instant

class CommonEvent(private var activity: MainActivity) : CommonInvokeListener {

    override fun continueProcess() = with(activity) {
        when {
            activeFragment in setOf(
                R.id.fragment_auth, R.id.fragment_splash
            ) -> true

            Instant.now().epochSecond > viewModel.loadRefreshTokenTimestampUseCase() -> {
                false
            }

            else -> true
        }
    }

    override fun setRefreshTokenResultListener(listener: CommonListener) {
        activity.listener = listener
    }

    override fun setCanRecallAPI(value: Boolean) {
        activity.viewModel.setCanRecallAPI(value)
    }

    override fun setNullListener() {
        activity.listener = null
    }
}