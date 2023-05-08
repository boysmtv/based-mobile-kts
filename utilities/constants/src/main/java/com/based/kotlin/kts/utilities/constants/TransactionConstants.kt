package com.based.kotlin.kts.utilities.constants

object TransactionConstants {
    
    const val SPV_BIG_NOMINAL_STATE = "spvBigNominal"
    const val KEY_CHANNEL = "channel"
    const val KEY_CUSTOMER_ID = "customerId"
    const val KEY_FULL_NAME = "fullName"
    const val KEY_IMG_TEMP = "imgTemp"
    const val KEY_KTP_TEMP = "ktpTemp"
    const val KEY_CHANNEL_NAME = "channelName"
    const val KEY_QUEUE_NO = "queueNo"
    const val KEY_NIK_TEMP = "nikTemp"
    const val KEY_CUSTOMER_TYPE = "customerType"
    const val KEY_FIRST_BRANCH_VISIT = "firstBranchVisit"
    const val LOV_CUSTOMER_LIST = "customer.declined.reason.list"
    const val LOV_GB_LIST = "general.banker.declined.reason.list"
    const val LOV_SV_LIST = "supervisor.verif.decline.reason.list"
    const val LOV_SPV_NON_FINANCIAL_LIST = "card.cancel.gb.reason.list"
    const val LOV_RECAPTURE_EKTP_CANCEL_VERIFICATION_LIST =
        "recapture.ektpdataverification.cancellation.reason"
    const val LOCKED_ACCOUNT_STATE = "lockedAccountState"
    const val DEPOSIT_SUBMIT_LIMIT_APPROVAL_STATE = "deposit-submit-limit-approval"
    const val BLACKLIST_PIN_LIST = "blacklist.pin.list"

    const val VERIFIED_BY_CIVIL_REGISTRATION = "VERIFIED_BY_DUKCAPIL"
    const val VERIFIED_BY_BANK = "VERIFIED_BY_BANK"
    const val NOT_VERIFIED_BY_DUKCAPIL = "NOT_VERIFIED_BY_DUKCAPIL"
    const val LOV_DEBIT_CARD_CANCEL_LIST = "card.cancel.reason.list"
    const val LOV_GB_CUSTOMER_DATA_MAINTENANCE_DECLINE_LIST = "general.banker.cdm.declined.reason.list"
    const val LOV_CUSTOMER_CUSTOMER_DATA_MAINTENANCE_DECLINE_LIST = "customer.cdm.declined.reason.list"
    const val PHONE_NUMBER = "phone_number"
    const val EMAIL = "email"
    const val IS_TRANSFER_ANOTHER_BANK = "isTransferAnotherBank"
    const val LOV_CANCEL_RECAPTURE_EKTP = "recapture.ektp.cancellation.reason.list"
    const val LOV_GB_DECLINE_SELF_SERVICE = "gb.declined.self.service"

    // Account Type
    const val NON_FINANCIAL = "NON_FINANCIAL"


    const val TIMER_DIALOG_HANDLER = 3000
    const val TIMER_DIALOG = 1000
    const val TIMER_DIALOG_WARKAT_VERIFICATION_FAILED = 3000
    const val TIMER_2000 = 2000
    const val REGEX_EMAIL = "email.regex"
    const val REGEX_PHONE_NUMBER = "phone.number.regex"
    const val COMA_PLUS = ",+"
    const val LOV_GENDER_LIST = "ntb.gender.list"
    const val LOV_BENEFICIARY_OWNER_RELATION_LIST = "beneficiary.owner.relation"
    const val LOV_CDM_MARITAL_STATUS_LIST = "cdm.customer.marital.status"

    //key bundle argument data
    const val KEY_RESERVATION_ID = "reservationId"
    const val KEY_IS_NEED_CUSTOMER_PROFILE = "isNeedCustomerProfile"
    const val KEY_TRANSITION_STATE = "transitionState"
    const val KEY_TRANSITION_CIF = "transitionCif"
    const val KEY_TRANSITION_CUSTOMER_TYPE = "transitionCustomerType"
    const val KEY_EMAIL = "email"
    const val KEY_PHONE_NUMBER = "phoneNumber"
    const val KEY_SCRIPT_SIGNATURE_DIALOG = "scriptSignDialog"
    //end region


    const val MULTIPLE_EDIT_TRUE = "true"

    // Argument Dialog Cancelation
    const val LOV_GB_CS_LIST = "general.banker.cs.declined.reason.list"
    const val LOV_CUSTOMER_DEPOSIT_LIST = "customer.declined.deposit.reason.list"
    const val LOV_GB_WARKAT_REASON_CANCEL = "general.banker.warkat.decline.reasons"
    const val LOV_CUSTOMER_DATA_MAINTENANCE_CANCEL_LIST = "customer.cdm.declined.reason.list"
    //endregion

    object SkpRefusal{
        const val LOV_CURRENT_ACCOUNT_REFUSAL_LIST = "reason.skp.overview"
        const val LOV_FIX_SKP_REFUSAL_LIST = "reason.skp.posting"
        const val LOV_FIX_SKP_BILYET_REFUSAL_LIST = "reason.skp.bilyet.posting"
        const val LOV_FIX_SKP_CEK_REFUSAL_LIST = "reason.skp.cek.posting"
        const val LOV_NON_SKP ="reason.nonskp"
        const val LOV_CURRENT_ACCOUNT_REFUSAL_BILYET_GIRO_LIST="reason.skp.bilyet"
        const val LOV_CURRENT_ACCOUNT_REFUSAL_CEK_LIST="reason.skp.cek"
    }

    //Argument Edit Transaction
    const val BRANCH_TRANSACTION_PURPOSE_LIST = "branch.transaction.purpose.list"
    const val BRANCH_SOURCE_FUND_LIST = "branch.source.fund.list"
    const val BRANCH_SOURCE_FUND_NON_INDIVIDUAL_LIST = "non.per.trx.source.fund.list"
    const val BRANCH_TRANSACTION_PURPOSE_NON_INDIVIDUAL_LIST = "non.per.transaction.purpose.list"
    //endregion

    // Argument Confirmation Transaction
    const val AT_EMAIL = "@"
    const val COMMA = ","
    const val TRANSACTION_ID = "transactionId"
    //endregion

    //Argument Transaction Detail Customer
    const val LOAN = "LOAN"
    //endregion

    //Dialog view sign
    const val SCALE_FACTOR_DEFAULT = 1.0f
    const val SCALE_FACTOR_MAX = 0.1f
    const val SCALE_FACTOR_MIN = 10.0f
    //endregion

    //Transaction Script
    const val BILYET_GIRO = "BILYET_GIRO"
    const val CEK = "CEK"
    const val DOCUMENT_TYPE_SCRIPT_FRONT = "CEK_PHOTO_FRONT"
    const val DOCUMENT_TYPE_SCRIPT_BACK = "CEK_PHOTO_BACK"
    const val DOCUMENT_TYPE_BILYET_GIRO = "BILYET_PHOTO"
    //endregion

    //Transaction Overview
    const val ZERO = 0
    const val FIFTY = 50
    //endregion

    //Debit Card Product Code
    const val VISA_GOLD = "GOLDVISA"
    // endregion

    //Argument Change Debit Card
    const val KEY_PRODUCT_CODE = "accountProductCode"
    const val KEY_IS_SUPERVISOR = "isSupervisor"
    const val KEY_EMBOSSING_OPTION = "embossingOption"
    //endregion

    //Debit Card Maintenance
    const val ACCOUNT_ACTIVATION = "ACCOUNT_ACTIVATION"
    const val WITH_EMBOSSING = "WITH_EMBOSSING"

    //Account Maintenance
    const val ACTIVATION_ACCOUNT = "Aktivasi Rekening"
    const val BLOCK_ACCOUNT = "Blokir Rekening"
    const val DENUNCIATION_ACCOUNT = "Pengaduan Rekening Nasabah"
    const val UPDATE_DENUNCIATION_ACCOUNT = "Pembaruan Pengaduan"
    const val RK_STATEMENT = "Pilih Periode Rekening Koran"
    const val STATUS_ONE = "1"
    const val STATUS_SIX = "6"

    const val DEFAULT_CURRENCY = "default.currency"

    const val REASON_SKP_POSTING = "reason.skp.posting"

    //Customer Data Maintenance
    const val TYPE_PHONE_NUMBER = "phoneNumber"
    const val TYPE_HOME_PHONE_NUMBER = "homePhoneNumber"
    const val TYPE_OFFICE_PHONE_NUMBER = "officePhoneNumber"
    const val TYPE_EMAIL = "email"

    object TermsAndConditionServiceCode {
        const val TRANSFER_SERVICE_CODE = "transfer-another-bank-tnc"
        const val OTHER_TRANSACTION_SERVICE_CODE = "confirmation-tnc"
    }

    const val FEEDBACK_LOCK_ACCOUNT = "Lock Account"
    const val FEEDBACK_INSUFFICIENT_BALANCE = "Jumlah uang tidak mencukupi"

    object ScenarioSubmit {
        const val SCENARIO_APPROVAL_DEPOSIT = "supervisor-approval-deposit"
        const val SCENARIO_APPROVAL_WITHDRAWAL = "supervisor-approval-withdrawal"
        const val SCENARIO_APPROVAL_DEBET_OVERBOOKING = "supervisor-approval-debet-overbooking"
        const val SCENARIO_APPROVAL_SWEEP_OVERBOOKING = "supervisor-approval-sweep-overbooking"
        const val SCENARIO_APPROVAL_SWEEP_WITHDRAWAL = "supervisor-approval-sweep-withdrawal"
        const val SCENARIO_INSUFFICIENT_BALANCE = "scenario-insufficient-balance"
        const val SCENARIO_SCRIPT_BLOCKED = "scenario-script-blocked"
    }

    object ScenarioSpvVerification {
        const val SCENARIO_SUPERVISOR_REJECTION_CHEQUE = "supervisor-rejection-cheque"
        const val SCENARIO_CONFIRMATION_REJECTION_CHEQUE = "confirmation-rejection-cheque"
        const val SCENARIO_RECONFIRMATION_REJECTION_CHEQUE = "reconfirmation-rejection-cheque"
        const val SCENARIO_CONFIRMATION_FIX_SKP = "confirmation-fix-skp-rejection"
        const val SPV_TRANSACTION_CONFIRMATION="transaction-supervisor-confirmation"
        const val SCENARIO_SHOW_DIALOG_WARKAT_DETENTION = "scenario_show_dialog_warkat_detention"
    }

    object ScenarioNonFinancial {
        const val SCENARIO_OVERVIEW = "transaction-overview-page"
        const val SCENARIO_OVERVIEW_NON_FINANCIAL = "transaction-overview-nonfin-page"
        const val SCENARIO_OVERVIEW_OLD_NON_FINANCIAL = "transaction-overview-old-nonfin-page"
        const val SCENARIO_CHANGE_CARD = "debit-maintenance-change-card"
        const val SCENARIO_CHANGE_CARD_EMBOSSING_NAME = "debit-maintenance-change-card-embossing-name"
        const val SCENARIO_CHANGE_CARD_EMBOSSING_NAME_SUPERVISOR =
            "debit-maintenance-change-card-embossing-name-supervisor"
        const val SCENARIO_CHANGE_CARD_VERIFICATION = "debit-maintenance-change-card-verification-data"
        const val SCENARIO_CHANGE_CARD_EMBOSSING_VERIFICATION =
            "debit-maintenance-change-card-embossing-verification-data"
        const val SCENARIO_CHANGE_CARD_RETRIEVAL_VERIFICATION =
            "debit-maintenance-change-card-retrieval-verification-data"
        const val SCENARIO_CARD_RETRIEVAL_VERIFICATION = "debit-maintenance-card-retrieval-verification-data"
        const val SCENARIO_CHANGE_CARD_CUSTOMER_VERIFICATION =
            "debit-maintenance-change-card-verification-customer-data"
        const val SCENARIO_CARD_RETRIEVAL_CUSTOMER_VERIFICATION =
            "debit-maintenance-card-retrieval-verification-data-customer"
        const val SCENARIO_CHANGE_CARD_OFFICER_VERIFICATION =
            "debit-maintenance-change-card-verification-officer-data"
        const val SCENARIO_CHANGE_CARD_RETRIEVAL_OFFICER_VERIFICATION =
            "debit-maintenance-change-card-retrieval-verification-officer-data"
        const val SCENARIO_CHANGE_CARD_DIRECT_VALIDATION_DATA =
            "debit-maintenance-change-card-direct-validation-data"
        const val SCENARIO_CHANGE_CARD_RETRIEVAL_DIRECT_VALIDATION_DATA =
            "debit-maintenance-change-card--retrieval-direct-validation-data"
        const val SCENARIO_CHANGE_CARD_SUCCEED = "debit-maintenance-change-card-verification-customer-execution"
        const val SCENARIO_CHANGE_CARD_EDIT = "change-card-transaction-edit"
        const val SCENARIO_CHANGE_CARD_EDIT_WITH_EMBOSSING = "change-card-transaction-edit-with-embossing"
        const val SCENARIO_UNBLOCKED_CARD = "debit-maintenance-unblock-card"
        const val SCENARIO_CHANGE_PIN = "debit-maintenance-change-pin-card"
        const val SCENARIO_REGISTER_VISA = "debit-maintenance-register-visa-card"
        const val SCENARIO_CARD_RETRIEVAL = "debit-maintenance-card-retrieval"
        const val SCENARIO_FEEDBACK_TO_RESULT = "non-financial-result"
        const val SCENARIO_BANKER_CONFIRMATION = "banker-confirmation"
        const val SCENARIO_BANKER_CONFIRMATION_EMBOSSING_NAME = "banker-confirmation-embossing-name"
        const val SCENARIO_BANKER_CONFIRMATION_CARD_RETRIEVAL = "banker-confirmation-card-retrieval"
        const val SCENARIO_UNBLOCK_REISSUE_PIN = "unblock-reissue-pin-flow"
        const val SCENARIO_CHANGE_CARD_SUPERVISOR = "debit-maintenance-change-card-supervisor"
        const val SCENARIO_UNBLOCKED_CARD_SUPERVISOR = "debit-maintenance-unblock-card-supervisor"
        const val SCENARIO_CHANGE_PIN_SUPERVISOR = "debit-maintenance-change-pin-card-supervisor"
        const val SCENARIO_REGISTER_VISA_SUPERVISOR =
            "debit-maintenance-register-visa-card-supervisor"
        const val SCENARIO_CHANGE_CARD_RETRIEVAL_SUPERVISOR =
            "debit-maintenance-change-card-retrieval-supervisor"
        const val SCENARIO_CONFIRMATION_CUSTOMER = "confirmation-customer-data"
        const val SCENARIO_CUSTOMER_DEBIT_VERIFICATION = "customer-data-debit-verification"
        const val SCENARIO_CUSTOMER_DEBIT_CONFIRMATION = "customer-data-debit-confirmation"
        const val SCENARIO_CUSTOMER_DATA_VERIFICATION = "customer-data-verification"
        const val SCENARIO_EDIT_CONFIRMATION_CUSTOMER_DATA = "edit-confirmation-customer-data"
        const val SCENARIO_DATA_AUTHENTICATION_WITH_OTP = "customer-data-authentication-with-otp"
        const val SCENARIO_DATA_AUTHENTICATION = "customer-data-authentication"
        const val SCENARIO_CUSTOMER_RATING_RESULT = "customer-rating-result"
        const val SCENARIO_ACCOUNT_MAINTENANCE_SUPERVISOR = "account-maintenance-supervisor"
        const val SCENARIO_ACCOUNT_MAINTENANCE_ACTIVATION = "am-activation-account"
        const val SCENARIO_ACCOUNT_MAINTENANCE_BLOCK = "am-block-account"
        const val SCENARIO_ACCOUNT_MAINTENANCE_DENUNCIATION = "am-denunciation-account"
        const val SCENARIO_ACCOUNT_MAINTENANCE_UPDATE_DENUNCIATION =
            "am-update-denunciation-account"
        const val SCENARIO_ACCOUNT_MAINTENANCE_EDIT = "am-transaction-edit"
    }

    object ScenarioEdit {
        const val DEFAULT_AMOUNT_LIMIT = 250000000
    }

    const val CAPTURE_INTERVAL = 2000L
    const val COMPRESS_QUALITY = 100
    const val SHOW_CAMERA = "SHOW_CAMERA"

    const val PATH = "path"
    const val BASE64 = "base64"
    const val URL = "url"

    const val BUTTON_TEXT = "buttonText"

    object TransferRequest {
        const val RTGS_REQUEST = "supervisor-transfer-rtgs"
        const val OVERBOOKING_REQUEST = "supervisor-transfer-overbooking"
        const val SWEEP_EXECUTION_REQUEST = "sweep-transfer-confirmation"
    }

    object ServiceCode {
        const val ACCOUNT_MAINTENANCE = "account-maintenance-service"
        const val ACCOUNT_MAINTENANCE_EDIT = "account-maintenance-edit-service"
        const val ACCOUNT_MAINTENANCE_BLOCK_SUPERVISOR = "block-account-supervisor"
        const val ACCOUNT_MAINTENANCE_UNBLOCK_SUPERVISOR = "unblock-account-supervisor"
        const val CUSTOMER_DATA_DEBIT_CONFIRMATION = "customer-data-debit-confirmation"
        const val APPROVAL_SPV_SELF_SERVICE = "approval-self-service"
    }
    
    //for maintenance
    //account Maintenance
    const val ACTOR_A = "A"
}