/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.splash.data.local

import com.based.kotlin.core.common.util.security.SecurePrefManager
import com.based.kotlin.utilities.constants.PreferenceConstants.Splash.PREF_KEY_IP_APP
import javax.inject.Inject

class SplashIpAppCache @Inject constructor(
    private val securePrefManager: SecurePrefManager
) {
    //region SET
    fun set(ip: String) {
        securePrefManager.setString(PREF_KEY_IP_APP, ip)
    }
    //endregion

    //region CLEAR
    fun clear() = securePrefManager.remove(PREF_KEY_IP_APP)
    //endregion
}