package com.based.kotlin.core.ui.dialog.header.base

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding
import com.based.kotlin.core.ui.dialog.header.model.BaseHeader
import com.based.kotlin.core.ui.dialog.header.model.BaseHeaderTransactionView
import com.based.kotlin.core.ui.extensions.hide
import com.based.kotlin.core.ui.extensions.show

abstract class BaseDialogDetailTransactionHeader<binding : ViewBinding, header : BaseHeader> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseContentHeader<binding, header>(context, attrs) {

    abstract val baseHeaderTransactionView: BaseHeaderTransactionView

    override fun generateView(data: header) {
        with(baseHeaderTransactionView) {
            titleDateTransaction.text = data.date.first
            valueDateTransaction.text = data.date.second
            if (data.oldBdsLabel.isNotEmpty()) statusOldBds.apply {
                text = data.oldBdsLabel
                show()
            }
            if (data.oldBdsLabel.isNotEmpty() && data.isCompanyTransaction) {
                validateShowLabel(data)
                titleTransaction.hide()
                descriptionTransaction.hide()
            }
            statusTransaction.apply {
                text = data.status.value
                setTextColor(data.status.textColor)
                backgroundTintList = ColorStateList.valueOf(data.status.background)
            }
            titleTransaction.text = data.title
            descriptionTransaction.text = data.detail
        }
    }

    private fun validateShowLabel(data: header) {
        with(baseHeaderTransactionView) {
            statusOldBds.apply {
                text = data.oldBdsLabel
                show()
            }
        }
    }
}