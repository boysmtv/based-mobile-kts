package com.based.kotlin.core.common.presentation.events

import com.based.kotlin.core.entity.dashboard.ReservationState
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

interface EventApiListener {
    fun observeState(response: (Result<ReservationState>) -> Unit)
    fun invokeState(
        deviceType: String, currentState: String,
        reservationId: String = EMPTY_STRING
    )

    fun isStateReserved(currentState: String): Boolean
}