package com.based.kotlin.feature.authentication.navigation

import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

interface AuthNavigation {
    fun navigateToDashboard(scenario: String)
    fun navigateToTransaction(state: String)
    fun navigateToCustomerLanding()
    fun navigateToCustomerVerification()
    fun navigateToRating()
    fun navigateToWaiting(scenario: String)
    fun navigateToCustomerInformation()
    fun navigateToRecapture()
    fun navigateToNumericAuthorization(scenario: String)
    fun navigateToDebitCardMaintenance()
}