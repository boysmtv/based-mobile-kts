package com.based.kotlin.core.ui.dialog.data.transfer

import androidx.annotation.Keep
import com.based.kotlin.core.ui.dialog.data.DialogOverbookingReceiver
import com.based.kotlin.core.ui.dialog.data.DialogScriptSource
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

@Keep
abstract class DialogTransferDetail(
    open val dialogTransferReceiver: DialogTransferReceiver,
    open val transactionMethod: Pair<String, String>,
    open val note: Pair<String, String>,
    open val userType: String
) {
    open var transactionId: String? = EMPTY_STRING
    open var purpose: Pair<String, String>? = Pair(EMPTY_STRING, EMPTY_STRING)
    open var dialogTransferSender: DialogTransferSender? = null
    open var dialogOverbookingReceiver: DialogOverbookingReceiver? = null
    open var dialogScriptSource: DialogScriptSource? = null
    open var dialogTransferAccountDebit: DialogTransferAccountDebit? = null
    open var sourceFund: Pair<String, String>? = Pair(EMPTY_STRING, EMPTY_STRING)
    open var transactionService: String? = EMPTY_STRING
}