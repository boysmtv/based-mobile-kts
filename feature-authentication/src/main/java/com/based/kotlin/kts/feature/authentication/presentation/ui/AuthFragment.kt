package com.based.kotlin.kts.feature.authentication.presentation.ui

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.based.kotlin.kts.core.util.constants.UserType.CUSTOMER
import com.based.kotlin.kts.core.util.constants.UserType.GB
import com.based.kotlin.kts.core.base.BaseActivity
import com.based.kotlin.kts.core.base.BaseFragment
import com.based.kotlin.kts.core.base.observeDataFlow
import com.based.kotlin.kts.core.base.softInputMode
import com.based.kotlin.kts.core.common.util.ArgumentConstants.NumericAuthorization.AUTH
import com.based.kotlin.kts.core.common.util.ArgumentConstants.Transaction.SCENARIO_TRANSACTION_SUBMIT_DEPOSIT
import com.based.kotlin.kts.core.common.util.LoginType
import com.based.kotlin.kts.core.common.util.PreferenceConstants.Auth.PREF_KEY_APP_IDLE_TIME
import com.based.kotlin.kts.core.common.util.PreferenceConstants.Auth.PREF_KEY_APP_IDLE_TIME_CUSTOMER
import com.based.kotlin.kts.core.common.util.RespondConstants.HttpCode.CODE_409
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40011
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40014
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40017
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40018
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40036
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40047
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40081
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40210
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40221
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40241
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40242
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40288
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40323
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40342
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40347
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40386
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40414
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40529
import com.based.kotlin.kts.core.common.util.RespondConstants.StatusCode.CODE_40564
import com.based.kotlin.kts.core.common.util.orReplaceWith
import com.based.kotlin.kts.core.common.util.showCustomToastFailed
import com.based.kotlin.kts.core.entity.auth.AuthResponse
import com.based.kotlin.kts.core.entity.auth.CurrentStateResponse
import com.based.kotlin.kts.core.ui.dialog.base.BaseDataDialog
import com.based.kotlin.kts.core.ui.extensions.setOnSingleClickListener
import com.based.kotlin.kts.core.ui.widget.button.MandiriSwitchButtonListener
import com.based.kotlin.kts.core.util.disable
import com.based.kotlin.kts.feature.authentication.R
import com.based.kotlin.kts.feature.authentication.R.drawable.ic_error
import com.based.kotlin.kts.feature.authentication.R.drawable.ic_error_multiple_device
import com.based.kotlin.kts.feature.authentication.databinding.FragmentAuthBinding
import com.based.kotlin.kts.feature.authentication.presentation.viewmodel.AuthViewModel
import com.based.kotlin.kts.utilities.constants.Constants.BUTTON_BACK
import com.based.kotlin.kts.utilities.constants.Constants.DEFAULT_APPLICATION_IDLE_TIME
import com.based.kotlin.kts.utilities.constants.Constants.DELAY
import com.based.kotlin.kts.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.kts.utilities.constants.Constants.ZERO
import com.based.kotlin.kts.utilities.constants.SseState.ADVERTISEMENT_WAIT_SUPERVISOR_ALLOCATION_PAGE
import com.based.kotlin.kts.utilities.constants.SseState.CONFIRM_FINISH_TRANSACTION_PAGE
import com.based.kotlin.kts.utilities.constants.SseState.DEPOSIT_SUBMIT_WAIT_PAGE
import com.based.kotlin.kts.utilities.constants.SseState.RECEIPT_WAIT_PAGE
import com.based.kotlin.kts.utilities.constants.SseState.REVIEW_PAGE
import com.based.kotlin.kts.utilities.constants.SseState.TRANSACTION_CONFIRMATION_OLD_WAIT_PAGE
import com.based.kotlin.kts.utilities.constants.SseState.TRANSACTION_CONFIRMATION_PAGE
import com.based.kotlin.kts.utilities.constants.SseState.TRANSACTION_OVERVIEW_WAIT_PAGE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    internal val viewModel by viewModels<AuthViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAuthBinding =
        FragmentAuthBinding.inflate(inflater, container, false)

    override fun initView() {
        softInputMode(SOFT_INPUT_ADJUST_RESIZE)
        observeLogin()
        observeLoginCustomer()
        observeReservationState()
        observeLoad()
        setupContent()
    }

    private fun observeReservationState() = with(viewModel) {
        observeDataFlow(
            postReservationState,
            onLoad = { showHideProgress(true) },
            onError = { showHideProgress(false) }
        ) {
            showHideProgress(false)
            navigateToWaiting()
        }
    }

    private fun setupContent() = with(binding) {
        setSwitchButton()
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

    private fun setSwitchButton() = with(binding.swButton) {
        setTextSelectFirst(translate(R.string.auth_segment_label_login_gb))
        setTextSelectSecond(translate(R.string.auth_segment_label_login_customer))
        setSwitchButtonListenerInterface(object : MandiriSwitchButtonListener {
            override fun onClickButtonFirst() {
                viewModel.userType = GB.value
            }

            override fun onClickButtonSecond() {
                viewModel.userType = CUSTOMER.value
            }

        })
    }

    private fun observeLoad() = with(viewModel) {
        loginData.observe(viewLifecycleOwner, loginDataObserver)
        loginCustomerData.observe(viewLifecycleOwner, loginCustomerDataObserver)
        loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        loadErrorMessage.observe(viewLifecycleOwner, errorMessageObserver)
    }

    private val loginDataObserver = Observer<AuthResponse> {
        Handler().postDelayed({
            viewModel.nav.navigateToDashboard(EMPTY_STRING)
        }, DELAY)
    }

    private val loginCustomerDataObserver = Observer<AuthResponse> {
        Handler().postDelayed({
            viewModel.nav.navigateToCustomerLanding()
        }, DELAY)
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
        onError = { observeOnErrorLogin(it) },
        onSuccess = {
            viewModel.dataAuthResponse = it
            viewModel.clearScenarioSubmit()
            observeOnSuccessLogin()
        }
    )

    private fun observeLoginCustomer() = observeDataFlow(
        viewModel.loginCustomer,
        onLoad = { showHideProgress(true) },
        onError = { observeOnErrorLogin(it) },
        onSuccess = {
            viewModel.dataCustomerAuthResponse = it
            viewModel.clearScenarioSubmit()
            observeOnSuccessLoginCustomer(it)
        }
    )

    private fun observeOnErrorLogin(it: Result<AuthResponse>) {
        showHideProgress(false)
        when {
            it.httpStatus == CODE_409 && it.code in setOf(
                CODE_40011, CODE_40323, CODE_40014, CODE_40017, CODE_40047,
                CODE_40018, CODE_40288, CODE_40221, CODE_40081, CODE_40036,
                CODE_40342, CODE_40210, CODE_40347, CODE_40241, CODE_40386,
                CODE_40414, CODE_40564, CODE_40529
            ) -> showDialogError(
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
        clearScenarioSubmitUseCase()
        dataAuthResponse.accessToken?.let { it1 -> validateNeedOtp(it1) }
            ?: navigateToOtp()
        (activity as? BaseActivity<*>)?.startSessionIdleFromFragment()
    }

    private fun observeOnSuccessLoginCustomer(authResponse: AuthResponse) = with(viewModel) {
        setIdleTimeByUserType(
            loadAppConfigUseCase(PREF_KEY_APP_IDLE_TIME_CUSTOMER)?.parameterValue.orReplaceWith(
                DEFAULT_APPLICATION_IDLE_TIME
            ).toLong()
        )
        saveLastSelectedTransactionUseCase(ZERO)
        showHideProgress(false)
        saveUserType(userType)
        saveDialogStateUseCase(false)
        authResponse.currentStateResponse?.reservationId?.let { id -> saveReservationId(id) }
        authResponse.branchNumber?.let { branchNumber -> saveBranchNumber(branchNumber) }
        if (authResponse.currentStateResponse != null) navigateBasedOnCurrentState(authResponse.currentStateResponse)
        else viewModel.nav.navigateToCustomerLanding()
        (activity as? BaseActivity<*>)?.startSessionIdleFromFragment()
    }

    private fun navigateBasedOnCurrentState(currentStateResponse: CurrentStateResponse?) = with(viewModel) {
        when {
            currentStateResponse?.isWaitingConfirmationSupervisor == true -> safeCallJob.run {
                postReservationConfirmationSupervisor()
            }

            currentStateResponse?.currentState == DEPOSIT_SUBMIT_WAIT_PAGE ||
                    currentStateResponse?.isWaitingConfirmation == true -> safeCallJob.run {
                postReservationConfirmation()
            }

            currentStateResponse?.currentState == TRANSACTION_CONFIRMATION_OLD_WAIT_PAGE ||
                    currentStateResponse?.isWaitingConfirmationOld == true -> safeCallJob.run {
                postReservationHybrid()
            }

            else -> navigateOtherBasedOnCurrentState(currentStateResponse)

        }
    }

    private fun navigateOtherBasedOnCurrentState(currentStateResponse: CurrentStateResponse?) =
        when (currentStateResponse?.currentState) {
            TRANSACTION_OVERVIEW_WAIT_PAGE -> viewModel.navigateToTransactionOverviewCustomer()
            TRANSACTION_CONFIRMATION_PAGE -> viewModel.navigateToTransactionConfirmationCustomer()
            in setOf(CONFIRM_FINISH_TRANSACTION_PAGE, REVIEW_PAGE) -> viewModel.nav.navigateToRating()
            RECEIPT_WAIT_PAGE -> viewModel.navigateToTransactionReceipt()
            else -> viewModel.nav.navigateToCustomerLanding()
        }

    private fun validateNeedOtp(token: String) {
        if (token.isEmpty()) navigateToOtp()
        else viewModel.nav.navigateToDashboard(EMPTY_STRING)
    }

    private fun navigateToWaiting() = with(viewModel) {
        when {
            dataCustomerAuthResponse.currentStateResponse?.isWaitingConfirmation == true ->
                nav.navigateToWaiting(SCENARIO_TRANSACTION_SUBMIT_DEPOSIT)

            dataCustomerAuthResponse.currentStateResponse?.isWaitingConfirmationSupervisor == true ->
                nav.navigateToWaiting(ADVERTISEMENT_WAIT_SUPERVISOR_ALLOCATION_PAGE)

            dataCustomerAuthResponse.currentStateResponse?.isWaitingConfirmationOld == true ->
                nav.navigateToWaiting(TRANSACTION_CONFIRMATION_OLD_WAIT_PAGE)

            else -> Unit
        }
    }

    private fun loginOnClickListener() = with(binding) {
        viewModel.saveInsuficientBalanceDialogUseCase(false)
        viewModel.userId = etUserNameAuth.text.toString()
        viewModel.password = etPasswordAuth.text.toString()

        if (viewModel.userType == GB.value) safeCallJob.run { viewModel.getLogin() }
        else safeCallJob.run { viewModel.getLoginCustomer() }
        viewModel.clearScenarioCache()
        viewModel.saveGbLoginType(LoginType.DUAL_SCREEN.name)
    }

    private fun loginEnableData() = with(binding) {
        btnLoginAuth.isEnabled = viewModel.isLoginButton(
            etUserNameAuth.text.toString(),
            etPasswordAuth.text.toString()
        )
    }

    private fun navigateToOtp() = with(viewModel) {
        nav.navigateToNumericAuthorization(AUTH)
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


