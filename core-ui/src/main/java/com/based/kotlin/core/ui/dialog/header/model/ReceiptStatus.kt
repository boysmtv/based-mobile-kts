package com.based.kotlin.core.ui.dialog.header.model

sealed class ReceiptStatus(open val status: String) {
    data class Success(override val status: String) : ReceiptStatus(status)
    data class InProcess(override val status: String) : ReceiptStatus(status)
    data class Failed(override val status: String) : ReceiptStatus(status)
}