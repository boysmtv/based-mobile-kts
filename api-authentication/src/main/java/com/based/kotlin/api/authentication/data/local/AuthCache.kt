package com.based.kotlin.api.authentication.data.local

import com.based.kotlin.core.common.util.JsonUtil
import com.based.kotlin.core.common.util.security.SecurePrefManager
import com.based.kotlin.core.entity.auth.AuthResponse
import com.based.kotlin.utilities.constants.PreferenceConstants.Auth.PREF_KEY_AUTH
import com.based.kotlin.utilities.constants.PreferenceConstants.Auth.PREF_KEY_REFRESH_TOKEN
import com.based.kotlin.utilities.constants.PreferenceConstants.Auth.PREF_KEY_TOKEN
import javax.inject.Inject

class AuthCache @Inject constructor(
    private val securePrefManager: SecurePrefManager,
    private val jsonUtil: JsonUtil
) {
    fun get(): AuthResponse? = securePrefManager.getString(PREF_KEY_AUTH)?.let {
        jsonUtil.fromJson<AuthResponse>(it)
    }

    fun set(authResponse: AuthResponse) {
        securePrefManager.setString(PREF_KEY_AUTH, jsonUtil.toJson(authResponse))
        securePrefManager.setString(PREF_KEY_TOKEN, authResponse.accessToken)
        securePrefManager.setString(PREF_KEY_REFRESH_TOKEN, authResponse.refreshToken)
    }

    fun getOtpResponse(): AuthResponse? = securePrefManager.getString(PREF_KEY_AUTH)?.let {
        jsonUtil.fromJson<AuthResponse>(it)
    }

    fun setOtpResponse(otpAuthResponse: AuthResponse) {
        securePrefManager.setString(PREF_KEY_AUTH, jsonUtil.toJson(otpAuthResponse))
        securePrefManager.setString(PREF_KEY_TOKEN, otpAuthResponse.accessToken)
        securePrefManager.setString(PREF_KEY_REFRESH_TOKEN, otpAuthResponse.refreshToken)
    }

    fun clear() = securePrefManager.remove(PREF_KEY_AUTH)
}