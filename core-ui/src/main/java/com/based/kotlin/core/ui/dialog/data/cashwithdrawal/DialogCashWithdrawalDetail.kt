package com.based.kotlin.core.ui.dialog.data.cashwithdrawal

import androidx.annotation.Keep
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

@Keep
abstract class DialogCashWithdrawalDetail(
    open val dialogCashWithdrawalExecutor: DialogCashWithdrawalExecutor,
    open val sourceAccount: DialogCashWithdrawalSourceAccount,
    open val transactionMethod: Pair<String, String>,
    open val note: Pair<String, String>,
    open val userType: String
) {
    open var transactionId: String? = EMPTY_STRING
    open var dialogCashWithdrawalAccountDebit: DialogCashWithdrawalAccountDebit? = null
    open var dialogCashWithdrawalCompanyInformation: DialogCashWithdrawalCompanyInformation? = null
    open var dialogCashWithdrawalSourceDetail: DialogCashWithdrawalExecutor? = null
}