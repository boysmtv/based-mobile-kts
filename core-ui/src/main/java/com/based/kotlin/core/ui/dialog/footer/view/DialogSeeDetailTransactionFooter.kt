package com.based.kotlin.core.ui.dialog.footer.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.based.kotlin.core.ui.databinding.FooterDetailTransactionBinding
import com.based.kotlin.core.ui.dialog.footer.model.DialogSeeDetailContentFooter
import com.based.kotlin.core.util.viewLayoutInflater

class DialogSeeDetailTransactionFooter(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val viewBinding = FooterDetailTransactionBinding.inflate(viewLayoutInflater, this, true)

    fun initialize(dialogSeeDetailContentFooter: DialogSeeDetailContentFooter, onClose: () -> Unit) {
        with(viewBinding.btnCloseTransaction) {
            text = dialogSeeDetailContentFooter.title
            setOnClickListener { onClose.invoke() }
        }
    }
}