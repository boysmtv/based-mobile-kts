package com.based.kotlin.feature.splash.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.based.kotlin.core.base.BaseFragment
import com.based.kotlin.core.base.observeDataFlow
import com.based.kotlin.core.common.util.AppVersion
import com.based.kotlin.core.data.SupportedLanguage
import com.based.kotlin.core.data.Translator
import com.based.kotlin.core.ui.dialog.data.BaseDataDialogGeneral
import com.based.kotlin.core.ui.extensions.hide
import com.based.kotlin.core.ui.extensions.show
import com.based.kotlin.core.util.IpAddressUtils
import com.based.kotlin.feature.splash.R
import com.based.kotlin.feature.splash.databinding.FragmentSplashBinding
import com.based.kotlin.feature.splash.presentation.viewmodel.SplashViewModel
import com.based.kotlin.utilities.constants.Constants.MAX_RETRY
import com.based.kotlin.utilities.constants.Constants.SPACE_STRING
import dagger.hilt.android.AndroidEntryPoint
import com.based.kotlin.core.R as coreR

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun initView() {
        viewModel.checkAppVersion()
        setupUi()
        observeConfigCheck()
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun onPause() {
        super.onPause()
        dismissDialogGeneralError()
    }

    private fun setupUi() {
        lifecycleScope.launchWhenResumed { viewModel.postConfigCheck() }
        binding.tvVersionSplash.text = translate(getString(R.string.splash_app_version_info_label))
            .plus(SPACE_STRING)
            .plus(AppVersion.VERSION_NAME)
    }

    private fun observeConfigCheck() = with(viewModel) {
        observeDataFlow(checkConfig,
            onLoad = {
                binding.pbSplash.show()
            },
            onError = {
                binding.pbSplash.hide()
                if (retryCount < MAX_RETRY) showGeneralError(
                    BaseDataDialogGeneral(
                        title = translate(coreR.string.dialog_general_error_title_under_maintenance),
                        message = translate(coreR.string.dialog_general_error_desc_under_maintenance),
                        icon = coreR.drawable.ic_server_no_response,
                        textPrimaryButton = translate(R.string.button_general_error_try_again),
                        secondaryIsVisible = retryCount >= MAX_RETRY,
                        visibleBackToSplash = true,
                        dismissOnAction = true
                    ),
                    actionClick = {
                        lifecycleScope.launchWhenResumed {
                            dismissDialogGeneralError()
                            viewModel.postConfigCheck()
                        }
                    }
                ) {}
            }
        ) {
            Translator.reload(SupportedLanguage.INDONESIAN)
            onAppConfigSuccess()
        }
    }

    private fun onAppConfigSuccess() {
        context?.let { IpAddressUtils.getWifiIpAddress(it) }?.let { viewModel.saveIp(it) }
        binding.pbSplash.hide()
        viewModel.navigateToAuthentication()
    }
}