/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.splash.domain.save

import com.based.kotlin.api.splash.data.repository.SplashRepository
import javax.inject.Inject

class SaveIpAppUseCase @Inject constructor(
    private val repo: SplashRepository
) {
    operator fun invoke(ip: String) = repo.saveIp(ip)
}