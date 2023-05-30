package com.based.kotlin.core.common.util

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

object AccessMenu {

    const val CASH_DETAILS = "CASH_DETAILS"
    const val ASSIGNED_CUSTOMER = "ASSIGNED_CUSTOMER"
    const val QUEUE_LIST = "QUEUE_LIST"
    const val START_RESERVATION = "START_RESERVATION"
    const val PENDING_LIST = "PENDING_LIST"
    const val HISTORY_LIST = "HISTORY_LIST"
    const val CUSTOMER_VERIFICATION_BY_SUPERVISOR = "CUSTOMER_VERIFICATION_BY_SUPERVISOR"
    const val DASHBOARD_HANDLING_DETAILS = "DASHBOARD_HANDLING_DETAILS"
    const val CUSTOMER_DETAILS = "CUSTOMER_DETAILS"
    const val TRANSACTION_OVERVIEW_DETAILS = "TRANSACTION_OVERVIEW_DETAILS"
    const val TODAY_RESERVATION_LIST = "TODAY_RESERVATION_LIST"
    const val ASSIGNED_CUSTOMER_CS = "ASSIGNED_CUSTOMER_CS"
    const val AUTO_ASSIGNED_CUSTOMER_CS = "AUTO_ASSIGNED_CUSTOMER_CS"
    const val DEBIT_SUPERVISOR_SERVE = "DEBIT_SUPERVISOR_SERVE"

    private var accessMenus: Set<String> = setOf()

    fun setAccessMenu(accessMenu: List<String>) {
        accessMenus = accessMenu.distinct().toSet()
    }

    private fun isMenuAccessible(accessMenu: Array<out String>) =
        accessMenus.any { it in accessMenu }

    fun isAccessible(vararg accessMenu: String) = isMenuAccessible(accessMenu)

    fun doIfAllowed(vararg accessMenu: String, action: () -> Unit) {
        if (isMenuAccessible(accessMenu)) action()
    }

    fun View.showIfAllowed(vararg accessMenu: String) {
        this.isVisible = accessMenus.any { it in accessMenu }
    }

    fun View.showIfAllowedInvisible(vararg accessMenu: String) {
        this.isInvisible = !accessMenus.any { it in accessMenu }
    }

    fun View.showIfAllowed(accessMenu: List<String>) {
        this.isVisible = accessMenus.any { it in accessMenu }
    }
}