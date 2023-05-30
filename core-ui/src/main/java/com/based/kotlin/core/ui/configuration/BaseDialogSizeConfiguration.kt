package com.based.kotlin.core.ui.configuration

import com.based.kotlin.core.ui.R

data class BaseDialogSizeConfiguration(
    val width: Int = R.dimen.transaction_dialog_detail_transaction_card_width,
    val height: Int = R.dimen.transaction_dialog_detail_transaction_card_height,
    val headerWidth: Int = R.dimen.transaction_dialog_detail_transaction_card_width,
    val headerHeight: Int = R.dimen.transaction_dialog_detail_transaction_background_header_height
)
