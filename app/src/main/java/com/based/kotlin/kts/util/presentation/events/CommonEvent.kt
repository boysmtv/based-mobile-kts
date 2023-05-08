package com.based.kotlin.kts.util.presentation.events

import com.based.kotlin.kts.R
import com.based.kotlin.kts.activity.MainActivity
import com.based.kotlin.kts.core.common.presentation.CommonInvokeListener
import com.based.kotlin.kts.core.common.presentation.CommonListener
import org.threeten.bp.Instant

class CommonEvent(private var activity: MainActivity) : CommonInvokeListener {

    override fun continueProcess() = with(activity) {
        when {
            activeFragment in setOf(R.id.fragment_auth, R.id.fragment_splash,
                R.id.fragment_numeric_authorization_auth) -> true
            Instant.now().epochSecond > viewModel.loadRefreshTokenTimestampUseCase() -> {
                getToken()
                false
            }
            else -> true
        }
    }

    override fun invalidRefreshToken() {
        with(activity) {
            viewModel.saveRefreshToken()
            getToken()
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