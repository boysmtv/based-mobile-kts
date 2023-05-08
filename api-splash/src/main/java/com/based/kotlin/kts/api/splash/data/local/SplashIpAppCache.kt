///*
// * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
// *
// * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// *
// */
//package com.based.kotlin.kts.api.splash.data.local
//
//import com.based.kotlin.kts.core.common.util.PreferenceConstants.Splash.PREF_KEY_IP_APP
//import com.based.kotlin.kts.core.common.util.security.SecurePrefManager
//import javax.inject.Inject
//
//class SplashIpAppCache @Inject constructor(
//    private val securePrefManager: SecurePrefManager
//) {
//    //region SET
//    fun set(ip: String) {
//        securePrefManager.setString(PREF_KEY_IP_APP, ip)
//    }
//    //endregion
//
//    //region CLEAR
//    fun clear() = securePrefManager.remove(PREF_KEY_IP_APP)
//    //endregion
//}