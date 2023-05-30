/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.splash.data.local

import javax.inject.Inject

class SplashIpAppLocalDataSource @Inject constructor(
    private val cache: SplashIpAppCache
) {
    //region SAVE
    fun saveIpCache(ip: String) = cache.set(ip)
    //endregion
}