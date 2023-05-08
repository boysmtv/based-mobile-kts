/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.kts.feature.authentication.presentation.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.based.kotlin.kts.core.base.BaseDialogFragment
import com.based.kotlin.kts.core.common.util.RespondConstants.HttpCode.CODE_500
import com.based.kotlin.kts.feature.authentication.R
import com.based.kotlin.kts.feature.authentication.databinding.FragmentDialogPopupMessagesLoginBinding
import com.based.kotlin.kts.feature.authentication.utils.AuthConstants.HTTP_STATUS
import com.based.kotlin.kts.utilities.constants.Constants.TITLE
import com.based.kotlin.kts.utilities.constants.Constants.DESCRIPTION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogPopupMessages : BaseDialogFragment<FragmentDialogPopupMessagesLoginBinding>() {

    override val injectedDialogFragment: DialogFragment = this

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDialogPopupMessagesLoginBinding = FragmentDialogPopupMessagesLoginBinding.inflate(
        inflater,
        container, false
    )

    override fun initView() {
        initData()
    }

    private fun initData() = with(mBinding) {
        tvTitleDialogPopupMessages.text = requireArguments().getString(TITLE).toString()
        tvDescDialogPopupMessages.text = requireArguments().getString(DESCRIPTION).toString()
        if (requireArguments().getString(HTTP_STATUS).toString() == CODE_500) {
            ivStatusDialogPopupMessages.setImageResource(R.drawable.ic_error_connection)
        } else ivStatusDialogPopupMessages.setImageResource(R.drawable.ic_close_rounded_red)

        ivCloseDialogPopupMessages.setOnClickListener { dismiss() }
    }
}