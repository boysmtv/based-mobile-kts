package com.based.kotlin.core.ui.dialog.footer.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.based.kotlin.core.ui.databinding.FooterDetailTransactionBinding
import com.based.kotlin.core.ui.dialog.footer.base.BaseContentFooter
import com.based.kotlin.core.ui.dialog.footer.model.DialogContentFooter
import com.based.kotlin.core.util.viewLayoutInflater

class DialogDetailTransactionFooter(
    context: Context,
    attrs: AttributeSet? = null
) : BaseContentFooter<FooterDetailTransactionBinding>(context, attrs) {

    override val buttonPrimaryAction: View by lazy { viewBinding.btnCloseTransaction }

    override fun bindLayout(): FooterDetailTransactionBinding {
        return FooterDetailTransactionBinding.inflate(viewLayoutInflater, this, true)
    }

    override fun generateView(data: DialogContentFooter) {
        viewBinding.btnCloseTransaction.text = data.title
    }
}