package com.based.kotlin.core.ui.dialog.data.transfer

import androidx.annotation.Keep
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

@Keep
data class DialogTransferReceiver(
    val label: String,
    val title: String,
    val name: String,
    val labelSource: String? = EMPTY_STRING,
    val productName: String,
    val accountNumber: String
)