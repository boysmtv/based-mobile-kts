package com.based.kotlin.feature.authentication.otp.presentation.ui

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.based.kotlin.core.base.BaseFragment
import com.based.kotlin.feature.authentication.otp.databinding.FragmentOtpAuthorizationBinding
import com.based.kotlin.feature.authentication.otp.presentation.viewmodel.OtpAuthorizationFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpAuthorizationFragment : BaseFragment<FragmentOtpAuthorizationBinding>() {

    internal val viewModel: OtpAuthorizationFragmentViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOtpAuthorizationBinding =
        FragmentOtpAuthorizationBinding.inflate(inflater, container, false)

    override fun initView() {
        showHideProgress(true)

        Handler(Looper.getMainLooper()).postDelayed({
            showHideProgress(false)
            viewModel.navigateToMainMenu()
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}


