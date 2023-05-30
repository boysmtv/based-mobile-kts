package com.based.kotlin.core.nav.utils

import android.net.Uri
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.NUMERIC_AUTH_VERIFICATION_LANDING
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.RATING_SERVICE
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.TRANSACTION_CONFIRMATION_CUSTOMER
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.TRANSACTION_EDIT
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.TRANSACTION_OVERVIEW_CUSTOMER
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.TRANSACTION_RECEIPT
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.WAITING_ADVERTISEMENT_SUPERVISOR
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.WAITING_CUSTOMER_LANDING
import com.based.kotlin.core.nav.utils.StateHelper.CustomerScreen.WAITING_CUSTOMER_SCREEN_STATUS
import com.based.kotlin.core.nav.utils.StateHelper.GbScreen.CUSTOMER_INFORMATION_VERIFICATION
import com.based.kotlin.core.nav.utils.StateHelper.GbScreen.NUMERIC_AUTH_OTP
import com.based.kotlin.core.nav.utils.StateHelper.GbScreen.OLD_BDS_REVIEW
import com.based.kotlin.core.nav.utils.StateHelper.GbScreen.SUPERVISOR_CONFIRMATION
import com.based.kotlin.core.nav.utils.StateHelper.GbScreen.TRANSACTION_CONFIRMATION_GB
import com.based.kotlin.core.nav.utils.StateHelper.GbScreen.TRANSACTION_OVERVIEW_GB
import com.based.kotlin.core.nav.utils.StateHelper.GbScreen.WAITING_TRANSITION
import com.based.kotlin.core.nav.utils.StateHelper.UriConstants.AUTHORITY
import com.based.kotlin.core.nav.utils.StateHelper.UriConstants.SCHEME
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_SCENARIO
import com.based.kotlin.core.util.ArgumentConstants.Transaction.SCENARIO_CUSTOMER_WAIT_ALLOCATION
import com.based.kotlin.core.util.ArgumentConstants.Transaction.SCENARIO_EDIT
import com.based.kotlin.core.util.ArgumentConstants.Transaction.SCENARIO_RATING
import com.based.kotlin.core.util.ArgumentConstants.Transaction.SCENARIO_SEND_RECEIPT
import com.based.kotlin.core.util.ArgumentConstants.Transaction.SCENARIO_TRANSACTION_SUBMIT
import com.based.kotlin.core.util.ArgumentConstants.Transaction.SCENARIO_TRANSACTION_SUBMIT_DEPOSIT
import com.based.kotlin.utilities.constants.SseState.ADVERTISEMENT_DONE_PAGE
import com.based.kotlin.utilities.constants.SseState.ADVERTISEMENT_PAGE
import com.based.kotlin.utilities.constants.SseState.ADVERTISEMENT_WAIT_SUPERVISOR_ALLOCATION_PAGE
import com.based.kotlin.utilities.constants.SseState.CHOOSE_SUPERVISOR_ALLOCATION_PAGE
import com.based.kotlin.utilities.constants.SseState.CONFIRM_FINISH_TRANSACTION_PAGE
import com.based.kotlin.utilities.constants.SseState.CUSTOMER_DETAILS_PAGE
import com.based.kotlin.utilities.constants.SseState.CUSTOMER_REVIEW_PAGE
import com.based.kotlin.utilities.constants.SseState.CUSTOMER_WAIT_ALLOCATION_PAGE
import com.based.kotlin.utilities.constants.SseState.DEPOSIT_SUBMIT_PAGE
import com.based.kotlin.utilities.constants.SseState.DEPOSIT_SUBMIT_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.DONE_RESERVATION
import com.based.kotlin.utilities.constants.SseState.GREETING_PAGE
import com.based.kotlin.utilities.constants.SseState.OTP_CUSTOMER_COUNTDOWN_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.OTP_CUSTOMER_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.OTP_SUPERVISOR_ALLOCATION_PAGE
import com.based.kotlin.utilities.constants.SseState.OTP_WITHDRAWAL_CUSTOMER_COUNTDOWN_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.RECEIPT_PAGE
import com.based.kotlin.utilities.constants.SseState.RECEIPT_RESEND_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.RECEIPT_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.REVIEW_PAGE
import com.based.kotlin.utilities.constants.SseState.REVIEW_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_CONFIRMATION_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_CONFIRMATION_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_EDIT_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_EDIT_PREPARATION_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_EDIT_PREPARATION_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_EDIT_SUBMIT_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_EDIT_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_OVERVIEW_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_OVERVIEW_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.VERIFICATION_SUPERVISOR_ALLOCATION_PAGE

object StateHelper {

    fun getStateDestination(state: String, isCustomer: Boolean = false): Uri.Builder? =
        if (isCustomer) onCustomerState(state)
        else onGbState(state)

    private fun onCustomerState(state: String) =
        when (state) {
            TRANSACTION_OVERVIEW_PAGE -> buildUri(TRANSACTION_OVERVIEW_CUSTOMER)
            TRANSACTION_CONFIRMATION_PAGE -> buildUri(TRANSACTION_CONFIRMATION_CUSTOMER)
            TRANSACTION_EDIT_PREPARATION_PAGE -> buildUri(NUMERIC_AUTH_VERIFICATION_LANDING)
            ADVERTISEMENT_WAIT_SUPERVISOR_ALLOCATION_PAGE,
            ADVERTISEMENT_DONE_PAGE -> buildUri(WAITING_ADVERTISEMENT_SUPERVISOR)
            CONFIRM_FINISH_TRANSACTION_PAGE,
            REVIEW_PAGE -> buildUri(RATING_SERVICE)
            CUSTOMER_WAIT_ALLOCATION_PAGE -> buildUri(WAITING_CUSTOMER_SCREEN_STATUS)
            RECEIPT_WAIT_PAGE -> buildUri(TRANSACTION_RECEIPT)
            TRANSACTION_EDIT_PAGE -> buildUri(TRANSACTION_EDIT)
            DONE_RESERVATION,
            ADVERTISEMENT_PAGE,
            GREETING_PAGE -> buildUri(WAITING_CUSTOMER_LANDING)
            else -> null
        }

    private fun onGbState(state: String) =
        when (state) {
            CUSTOMER_DETAILS_PAGE -> buildUri(CUSTOMER_INFORMATION_VERIFICATION)
            CUSTOMER_REVIEW_PAGE -> buildUri(OLD_BDS_REVIEW)
            TRANSACTION_OVERVIEW_PAGE -> buildUri(TRANSACTION_OVERVIEW_GB)
            TRANSACTION_OVERVIEW_WAIT_PAGE -> buildUri(TRANSACTION_OVERVIEW_GB)
            TRANSACTION_CONFIRMATION_WAIT_PAGE -> buildUri(TRANSACTION_CONFIRMATION_GB)
            OTP_CUSTOMER_WAIT_PAGE,
            OTP_CUSTOMER_COUNTDOWN_WAIT_PAGE,
            OTP_WITHDRAWAL_CUSTOMER_COUNTDOWN_WAIT_PAGE,
            TRANSACTION_EDIT_WAIT_PAGE,
            TRANSACTION_EDIT_PREPARATION_WAIT_PAGE,
            TRANSACTION_EDIT_SUBMIT_WAIT_PAGE,
            DEPOSIT_SUBMIT_WAIT_PAGE -> buildUri(WAITING_CUSTOMER_SCREEN_STATUS)
            CHOOSE_SUPERVISOR_ALLOCATION_PAGE -> buildUri(SUPERVISOR_CONFIRMATION)
            DEPOSIT_SUBMIT_PAGE -> buildUri(TRANSACTION_CONFIRMATION_GB)
            VERIFICATION_SUPERVISOR_ALLOCATION_PAGE -> buildUri(WAITING_TRANSITION)
            OTP_SUPERVISOR_ALLOCATION_PAGE -> buildUri(NUMERIC_AUTH_OTP)
            REVIEW_WAIT_PAGE -> buildUri(WAITING_CUSTOMER_SCREEN_STATUS)
            RECEIPT_PAGE -> buildUri(TRANSACTION_RECEIPT)
            RECEIPT_RESEND_WAIT_PAGE -> buildUri(WAITING_CUSTOMER_SCREEN_STATUS)
            else -> null
        }

    fun mapCustomerStatusScenario(state: String, uri: Uri.Builder): Uri.Builder =
        when (state) {
            OTP_CUSTOMER_WAIT_PAGE,
            OTP_WITHDRAWAL_CUSTOMER_COUNTDOWN_WAIT_PAGE ->
                uri.appendQueryParameter(ARGS_KEY_SCENARIO, SCENARIO_TRANSACTION_SUBMIT)
            REVIEW_WAIT_PAGE -> uri.appendQueryParameter(ARGS_KEY_SCENARIO, SCENARIO_RATING)
            TRANSACTION_EDIT_WAIT_PAGE,
            TRANSACTION_EDIT_SUBMIT_WAIT_PAGE -> uri.appendQueryParameter(
                ARGS_KEY_SCENARIO,
                SCENARIO_EDIT
            )
            DEPOSIT_SUBMIT_WAIT_PAGE -> uri.appendQueryParameter(
                ARGS_KEY_SCENARIO,
                SCENARIO_TRANSACTION_SUBMIT_DEPOSIT
            )
            CUSTOMER_WAIT_ALLOCATION_PAGE -> uri.appendQueryParameter(
                ARGS_KEY_SCENARIO,
                SCENARIO_CUSTOMER_WAIT_ALLOCATION
            )
            RECEIPT_RESEND_WAIT_PAGE -> uri.appendQueryParameter(
                ARGS_KEY_SCENARIO,
                SCENARIO_SEND_RECEIPT
            )
            else -> uri
        }

    private fun buildUri(path: String) =
        Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(path)

    object UriConstants {
        const val SCHEME = "based.kotlin.id"
        const val AUTHORITY = "learn"
    }

    object CustomerScreen {
        const val TRANSACTION_OVERVIEW_CUSTOMER = "transaction-overview-customer"
        const val TRANSACTION_CONFIRMATION_CUSTOMER = "transaction-confirmation-customer"
        const val TRANSACTION_RECEIPT = "transaction-receipt"
        const val TRANSACTION_EDIT = "transaction-edit"
        const val WAITING_ADVERTISEMENT_SUPERVISOR = "waiting-advertisement-supervisor"
        const val WAITING_CUSTOMER_SCREEN_STATUS = "waiting-customer-screen-status"
        const val WAITING_CUSTOMER_LANDING = "waiting-customer-landing"
        const val RATING_SERVICE = "rating-service"
        const val NUMERIC_AUTH_VERIFICATION_LANDING = "numeric-authorization-verification-landing"
    }

    object GbScreen {
        const val TRANSACTION_OVERVIEW_GB = "transaction-overview-gb"
        const val TRANSACTION_CONFIRMATION_GB = "transaction-confirmation-gb"
        const val CUSTOMER_INFORMATION_VERIFICATION = "customer-information-verification"
        const val SUPERVISOR_CONFIRMATION = "supervisor-confirmation"
        const val OLD_BDS_REVIEW = "old-bds-review"
        const val WAITING_TRANSITION = "waiting-transition"
        const val NUMERIC_AUTH_OTP = "numeric-auth-otp"
    }
}