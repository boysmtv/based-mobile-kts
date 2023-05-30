/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.splash.domain.load

import com.based.kotlin.api.splash.data.repository.SplashRepository
import javax.inject.Inject

class LoadAppConfigUseCase @Inject constructor(
    private val repo: SplashRepository
) {
    operator fun invoke(key: String) = repo.loadAppConfig(key)
}