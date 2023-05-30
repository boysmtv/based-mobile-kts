package com.based.kotlin.core.ui.dialog.header.model

abstract class BaseHeader(
    open val date: Pair<String, String>,
    open val status: Status,
    open val title: String,
    open val detail: CharSequence,
    open val oldBdsLabel: String,
    open val isCompanyTransaction: Boolean,
    open val userType: String? = null
)