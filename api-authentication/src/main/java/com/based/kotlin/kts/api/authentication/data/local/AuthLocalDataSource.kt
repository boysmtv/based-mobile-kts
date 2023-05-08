package com.based.kotlin.kts.api.authentication.data.local

import com.based.kotlin.kts.core.common.util.AccessMenu.setAccessMenu
import com.based.kotlin.kts.core.common.util.security.SecurePrefManager
import com.based.kotlin.kts.core.entity.auth.AuthResponse
import com.based.kotlin.kts.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.kts.utilities.constants.Constants.REFRESH_TOKEN_DELAY
import com.based.kotlin.kts.utilities.constants.PreferenceConstants.Auth.PREF_KEY_GB_LOGIN_TYPE
import com.based.kotlin.kts.utilities.constants.PreferenceConstants.Auth.PREF_KEY_REFRESH_TOKEN
import com.based.kotlin.kts.utilities.constants.PreferenceConstants.Auth.PREF_KEY_REFRESH_TOKEN_TIMESTAMP
import com.based.kotlin.kts.utilities.constants.PreferenceConstants.Auth.PREF_KEY_TOKEN
import com.based.kotlin.kts.utilities.constants.PreferenceConstants.Auth.PREF_KEY_USER_ID
import com.based.kotlin.kts.utilities.constants.PreferenceConstants.Auth.PREF_KEY_USER_TYPE
import org.threeten.bp.Instant
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val authCache: AuthCache,
    private val securePrefManager: SecurePrefManager
) {
    //region SAVE
    fun saveLoginData(authResponse: AuthResponse) = authCache.set(authResponse)

    fun saveUserId(userId: String) = securePrefManager.setString(PREF_KEY_USER_ID, userId)

    fun saveUserType(userType: String) {
        securePrefManager.setString(PREF_KEY_USER_TYPE, userType)
    }

    fun saveOtpResponse(otpAuthResponse: AuthResponse) {
        authCache.setOtpResponse(otpAuthResponse)
        setAccessMenu(otpAuthResponse.accessMenu)
    }

    fun saveAccessToken(accessToken: String, refreshToken: String = EMPTY_STRING) {
        securePrefManager.setString(PREF_KEY_TOKEN, accessToken)
        if (refreshToken.isNotBlank()) saveRefreshToken(refreshToken)
    }

    private fun saveRefreshToken(refreshToken: String) {
        securePrefManager.setString(PREF_KEY_REFRESH_TOKEN, refreshToken)
    }

    fun saveRefreshTokenTimestamp(timestamp: Long) {
        securePrefManager.setLong(
            PREF_KEY_REFRESH_TOKEN_TIMESTAMP,
            Instant.now().epochSecond + timestamp - REFRESH_TOKEN_DELAY
        )
    }
    fun saveGbLoginType(gbLoginType: String) {
        securePrefManager.setString(PREF_KEY_GB_LOGIN_TYPE, gbLoginType)
    }

    //region LOAD
    fun loadLoginData() = authCache.get()

    fun loadUserId() = securePrefManager.getString(PREF_KEY_USER_ID)

    fun loadUserType() = securePrefManager.getString(PREF_KEY_USER_TYPE)

    fun loadOptResponse() = authCache.getOtpResponse()

    fun loadRefreshToken() = securePrefManager.getString(PREF_KEY_REFRESH_TOKEN).orEmpty()

    fun loadRefreshTokenTimestamp() = securePrefManager.getLong(PREF_KEY_REFRESH_TOKEN_TIMESTAMP)

    fun loadGbLoginType() = securePrefManager.getString(PREF_KEY_GB_LOGIN_TYPE)
    //endregion

}