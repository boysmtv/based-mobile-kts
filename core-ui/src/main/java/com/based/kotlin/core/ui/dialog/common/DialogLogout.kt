package com.based.kotlin.core.ui.dialog.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.based.kotlin.core.ui.databinding.DialogLogoutBinding
import com.based.kotlin.core.ui.dialog.base.BaseDataDialog
import com.based.kotlin.core.ui.extensions.hide
import com.based.kotlin.core.ui.extensions.show

class DialogLogout(
    private val onClickButtonPrimary: () -> Unit,
    private val onClickButtonSecondary: () -> Unit,
    private val onClickButtonBack: () -> Unit,
    var data: BaseDataDialog
) : DialogFragment() {

    lateinit var binding: DialogLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCanceledOnTouchOutside(false)
    }

    fun setDialog(data: BaseDataDialog) {
        this.data = data
    }

    private fun setupView() = with(binding) {
        tvDialogThreeActionTitle.text = data.title
        tvDialogThreeActionDesc.text = data.content
        tvDialogThreeActionPrimaryButtonLabel.text = data.primaryButtonText

        if (data.secondaryButtonShow) {
            rlDialogThreeActionSecondaryBtn.show()
            tvDialogThreeActionSecondaryButtonLabel.text = data.secondaryButtonText
            data.secondaryButtonIcon?.let {
                ivDialogThreeActionSecondaryButton.show()
                ivDialogThreeActionSecondaryButton.setImageResource(it)
            }
        } else rlDialogThreeActionSecondaryBtn.hide()

        if (data.primaryButtonShow) {
            rlDialogThreeActionPrimaryBtn.show()
            tvDialogThreeActionPrimaryButtonLabel.text = data.primaryButtonText
            data.primaryButtonIcon?.let {
                ivDialogThreeActionPrimaryButton.show()
                ivDialogThreeActionPrimaryButton.setImageResource(it)
            }
        } else rlDialogThreeActionPrimaryBtn.hide()
    }

    private fun setupListeners() = with(binding) {
        rlDialogThreeActionPrimaryBtn.setOnClickListener {
            onClickButtonPrimary()
            dismiss()
        }

        rlDialogThreeActionSecondaryBtn.setOnClickListener {
            onClickButtonSecondary()
            dismiss()
        }

        tvDialogThreeActionBack.setOnClickListener {
            onClickButtonBack()
            dismiss()
        }
    }
}