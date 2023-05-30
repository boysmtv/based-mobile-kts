package com.based.kotlin.feature.authentication.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.based.kotlin.core.base.BaseActivity
import com.based.kotlin.core.base.BaseFragment
import com.based.kotlin.core.base.observeDataFlow
import com.based.kotlin.core.base.softInputMode
import com.based.kotlin.core.common.entity.Result
import com.based.kotlin.core.common.util.orReplaceWith
import com.based.kotlin.core.entity.auth.AuthResponse
import com.based.kotlin.core.entity.auth.CurrentStateResponse
import com.based.kotlin.core.ui.dialog.base.BaseDataDialog
import com.based.kotlin.core.ui.extensions.setOnSingleClickListener
import com.based.kotlin.core.util.ArgumentConstants.NumericAuthorization.AUTH
import com.based.kotlin.core.util.disable
import com.based.kotlin.core.util.showCustomToastFailed
import com.based.kotlin.feature.authentication.R
import com.based.kotlin.feature.authentication.R.drawable.ic_error
import com.based.kotlin.feature.authentication.R.drawable.ic_error_multiple_device
import com.based.kotlin.feature.authentication.databinding.FragmentAuthBinding
import com.based.kotlin.feature.authentication.presentation.viewmodel.AuthViewModel
import com.based.kotlin.utilities.constants.Constants.BUTTON_BACK
import com.based.kotlin.utilities.constants.Constants.DEFAULT_APPLICATION_IDLE_TIME
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.utilities.constants.PreferenceConstants.Auth.PREF_KEY_APP_IDLE_TIME
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_409
import com.based.kotlin.utilities.constants.RespondConstants.StatusCode.CODE_40242
import com.based.kotlin.utilities.constants.SseState.DEPOSIT_SUBMIT_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.RECEIPT_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.REVIEW_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_CONFIRMATION_OLD_WAIT_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_CONFIRMATION_PAGE
import com.based.kotlin.utilities.constants.SseState.TRANSACTION_OVERVIEW_WAIT_PAGE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    internal val viewModel by viewModels<AuthViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAuthBinding =
        FragmentAuthBinding.inflate(inflater, container, false)

    override fun initView() {
        softInputMode(SOFT_INPUT_ADJUST_RESIZE)
        observeLogin()
        observeLoad()
        setupContent()
    }

    private fun setupContent() = with(binding) {
        btnLoginAuth.disable()
        tilPasswordAuth.clearFocus()
        btnLoginAuth.setOnSingleClickListener { loginOnClickListener() }
        etUserNameAuth.addTextChangedListener {
            loginEnableData()
            if (tilUserNameAuth.isErrorEnabled) tilUserNameAuth.isErrorEnabled = false
        }
        etPasswordAuth.addTextChangedListener {
            loginEnableData()
            if (tilPasswordAuth.isErrorEnabled) tilPasswordAuth.isErrorEnabled = false
        }
    }

    private fun observeLoad() = with(viewModel) {
        loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        loadErrorMessage.observe(viewLifecycleOwner, errorMessageObserver)
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        showHideProgress(isLoading)
        binding.tilPasswordAuth.isEnabled = !isLoading
        binding.tilUserNameAuth.isEnabled = !isLoading
        binding.btnLoginAuth.isEnabled = !isLoading
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        if (isError == true) {
            val loginErrorWrong = viewModel.errorMessage

            Toast(context).showCustomToastFailed(
                loginErrorWrong,
                requireActivity()
            )

            binding.tilUserNameAuth.apply {
                isErrorEnabled = false
                setErrorIconDrawable(R.drawable.ic_tailing)
                error = loginErrorWrong
            }

            binding.tilPasswordAuth.apply {
                setErrorIconDrawable(R.drawable.ic_tailing)
                error = loginErrorWrong
            }
        }
    }

    private val errorMessageObserver = Observer<String> { viewModel.errorMessage = it }

    private fun observeLogin() = observeDataFlow(
        viewModel.login,
        onLoad = { showHideProgress(true) },
        onError = { observeOnErrorLogin(it) }
    ) {
        viewModel.dataAuthResponse = it
        observeOnSuccessLogin()
    }

    private fun observeOnErrorLogin(it: Result<AuthResponse>) {
        showHideProgress(false)
        when {
            it.httpStatus == CODE_409 -> showDialogError(
                icon = ic_error,
                title = it.title.toString(),
                message = it.message.toString()
            )

            it.httpStatus == CODE_409 && it.code == CODE_40242 -> showDialogError(
                icon = ic_error_multiple_device,
                title = it.title.toString(),
                message = it.message.toString()
            )

            else -> errorHandler(it.code, it.title, it.message, it.httpStatus)
        }
    }

    private fun observeOnSuccessLogin() = with(viewModel) {
        showHideProgress(false)
        setIdleTimeByUserType(
            loadAppConfigUseCase(PREF_KEY_APP_IDLE_TIME)?.parameterValue.orReplaceWith(
                DEFAULT_APPLICATION_IDLE_TIME
            ).toLong()
        )
        dataAuthResponse.accessToken?.let { validateNeedOtp(it) } ?: navigateToNumericAuthorizationAuth()
        (activity as? BaseActivity<*>)?.startSessionIdleFromFragment()
    }

    private fun validateNeedOtp(token: String) = with(viewModel) {
        if (token.isEmpty()) navigateToNumericAuthorizationAuth()
        else navigateToMainMenu()
    }

    private fun navigateOtherBasedOnCurrentState(currentStateResponse: CurrentStateResponse?) =
        when (currentStateResponse?.currentState) {
            TRANSACTION_OVERVIEW_WAIT_PAGE -> viewModel.navigateToTransactionOverviewCustomer()
            TRANSACTION_CONFIRMATION_PAGE -> viewModel.navigateToTransactionConfirmationCustomer()
            RECEIPT_WAIT_PAGE -> viewModel.navigateToTransactionReceipt()
            else -> viewModel.navigateToTransactionOverviewCustomer()
        }

    private fun loginOnClickListener() = with(binding) {
        viewModel.userId = etUserNameAuth.text.toString()
        viewModel.password = etPasswordAuth.text.toString()
        safeCallJob.run { viewModel.getLogin() }
    }

    private fun loginEnableData() = with(binding) {
        btnLoginAuth.isEnabled = viewModel.isLoginButton(
            etUserNameAuth.text.toString(),
            etPasswordAuth.text.toString()
        )
    }

    private fun showDialogError(
        icon: Int?,
        title: String?,
        message: String?
    ) {
        val content = BaseDataDialog(
            title.toString(),
            message.toString(),
            secondaryButtonShow = false,
            primaryButtonShow = true,
            primaryButtonText = BUTTON_BACK,
            secondaryButtonText = EMPTY_STRING,
            icon = icon,
            isIconShow = true
        )

        showDialogWithActionButton(
            dataToDialog = content,
            actionClickPrimary = {},
            actionClickSecondary = {},
            AuthFragment::class.simpleName.orEmpty()
        )
    }

    private fun setIdleTimeByUserType(time: Long) {
        (activity as? BaseActivity<*>)?.setIdleTimeByUserType(time)
    }
}


