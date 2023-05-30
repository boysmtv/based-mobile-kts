package com.based.kotlin.core.ui.contract

import androidx.viewbinding.ViewBinding

interface ViewContract<binding : ViewBinding, data> {
    fun bindLayout(): binding
    val viewBinding: binding
    fun generateView(data: data)
}