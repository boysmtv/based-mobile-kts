package com.based.kotlin.kts.core.common.presentation.events

import com.based.kotlin.kts.core.entity.dashboard.ReservationState
import com.based.kotlin.kts.utilities.constants.Constants.EMPTY_STRING

interface EventApiListener {
    fun observeState(response: (Result<ReservationState>) -> Unit)
    fun invokeState(
        deviceType: String, currentState: String,
        reservationId: String = EMPTY_STRING
    )

    fun isStateReserved(currentState: String): Boolean
}