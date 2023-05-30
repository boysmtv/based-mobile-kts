package com.based.kotlin.core.util

object ArgumentConstants {

    object Common {
        const val ARGS_KEY_ORIGIN = "origin-flow"
        const val ARGS_KEY_SCENARIO = "scenario"
        const val ARGS_KEY_SERVICE_CODE = "service-code"
        const val ARGS_KEY_TRANSITION_IN = "transition-in"
        const val ARGS_KEY_SELECTED_ID = "selected-id"
        const val ARGS_KEY_ENTRY_POINT = "entry-point"
        const val ARGS_KEY_LAST_STATEMENT_DATE = "lastStatementDate"
        const val ARGS_KEY_CARD_NUMBER = "cardNumber"
        const val ARGS_KEY_GUIDELINE_SEND_DEBIT = "guidelineSendDebit"
        const val KEY_TRANSITION_STATE = "transitionState"

        const val ARGS_KEY_STATE_START_SERVICE = "startService"
        const val ARGS_KEY_STATE_VIEW_DETAIL = "viewDetail"
        const val ARGS_KEY_STATE_OLD_BDS_SEE_DETAILS = "oldBdsDetails"
        const val ARGS_KEY_ACTION_FROM_DASHBOARD = "actionFromDashboard"
        const val ARGS_KEY_CHANNEL = "channel"
        const val ARGS_KEY_CUSTOMER_PHOTO = "customerPhoto"

        const val ARGS_KEY_OTP_SPV_STATE = "inputOtpState"
        const val ARGS_KEY_OTP_SPV_PHONE_NUMBER = "supervisorPhoneNumber"
        const val ARGS_KEY_OTP_SPV_RETRY = "supervisorRetryOtpCount"
        const val ARGS_KEY_CDM_STATE = "cdmState"
        const val ARGS_KEY_GET_LIST_UNDERLYING_DOCUMENTS = "getListUnderlyingDocuments"


        const val ARGS_KEY_MAIN_MENU = "guidelineSendDebit"

    }

    object Auth {
        const val CUSTOMER = "customer"
    }

    object NumericAuthorization {
        const val AUTH = "auth"
        const val PIN = "pin"
        const val OTP = "otp"
        const val IS_FROM_PIN = "isFromPin"
        const val IS_FROM_MULTI_COMPANY = "isFromMultiCompany"
    }

    object VerificationType {
        const val PIN = "PIN"
        const val OTP = "OTP"
    }

    object Transaction {
        const val OLD_BDS = "oldBds"
        const val SCENARIO_EDIT = "scenario_edit"
        const val SCENARIO_SEND_RECEIPT = "scenario_send_receipt"
        const val SCENARIO_RATING = "scenario_rating"
        const val SCENARIO_TRANSACTION_SUBMIT = "scenario_transaction_submit"
        const val SCENARIO_TRANSACTION_SUBMIT_DEPOSIT = "scenario_transaction_submit_deposit"
        const val SCENARIO_TRANSACTION_INSUFFICIENT_WAIT_PAGE =
            "scenario_transaction_insufficient_wait_page"
        const val SCENARIO_TRANSACTION_CONFIRMATION = "scenario_transaction_confirmation"
        const val SCENARIO_EDIT_TRANSACTION = "scenario_edit_transaction"
        const val SCENARIO_REVIEW_PAGE = "scenario_review-page"
        const val SCENARIO_CUSTOMER_WAIT_ALLOCATION = "scenario_customer-wait-allocation"
        const val SCENARIO_VIEW_DETAILS = "viewDetail"
        const val SCENARIO_CUSTOMER_WAIT_SUPERVISOR_CONFIRMATION =
            "scenario_customer_wait_supervisor_confirmation"
        const val SCENARIO_EDIT_CONFIRMATION_TRANSACTION = "edit_confirmation_transaction"
        const val SCENARIO_EDIT_TRANSACTION_OVERVIEW = "edit_transaction_overview"
        const val SCENARIO_SCRIPT_CHECK = "scenario_script_check"
        const val SCENARIO_SCRIPT_VIEW = "scenario_script_view"
        const val SCENARIO_WARKAT_AUTHENTICATION_WAIT_PAGE = "scenario_warkat_authentication_wait_page"
        const val SCENARIO_PREVIEW_SKP_GB = "scenario_preview_skp_gb"
        const val SCENARIO_PREVIEW_SKP_CUSTOMER = "scenario_preview_skp_cs"
        const val SCENARIO_PREVIEW_SKP_ACCEPTED_SUPERVISOR = "scenario_preview_skp_accepted_supervisor"
    }

    object Waiting {
        const val SCENARIO_WAITING_CUSTOMER_SUPERVISOR_CONFIRMATION =
            "scenario_waiting_customer_supervisor_confirmation"
        const val SCENARIO_WAITING_CUSTOMER_LANDING = "scenario_waiting_customer_landing"
        const val SCENARIO_WAITING_CUSTOMER_CONFIRMATION = "scenario_waiting_customer_confirmation"
    }

    object ScenarioCustomerInformation {
        const val SCENARIO_START_RESERVATION = "customer-start-reservation"
        const val SCENARIO_SPV_SELF_SERVICE = "spv-customer-information-self-service"
    }
}
