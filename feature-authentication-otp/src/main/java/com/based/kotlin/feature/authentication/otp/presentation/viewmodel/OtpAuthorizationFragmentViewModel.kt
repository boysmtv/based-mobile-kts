package com.based.kotlin.feature.authentication.otp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.based.kotlin.core.common.navigation.DeeplinkNavigation
import com.based.kotlin.utilities.constants.MainMenuDeeplink
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtpAuthorizationFragmentViewModel @Inject constructor(
    private val deeplinkNavigation: DeeplinkNavigation
) : ViewModel() {

    fun navigateToMainMenu() = deeplinkNavigation.navigate(MainMenuDeeplink.NAVIGATE_TO_MAIN_MENU)

}