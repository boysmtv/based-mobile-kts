package com.based.kotlin.kts.feature.authentication.navigation

interface AuthNavigation {
    fun navigateToDashboard(scenario: String)
    fun navigateToCustomerLanding()
    fun navigateToCustomerVerification()
    fun navigateToRating()
    fun navigateToWaiting(scenario: String)
    fun navigateToCustomerInformation()
    fun navigateToRecapture()
    fun navigateToNumericAuthorization(scenario: String)
    fun navigateToDebitCardMaintenance()
}