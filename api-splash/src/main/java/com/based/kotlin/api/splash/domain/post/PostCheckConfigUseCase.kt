/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.splash.domain.post

import com.based.kotlin.api.splash.data.repository.SplashRepository
import com.based.kotlin.core.entity.splash.ConfigCheckRequest
import javax.inject.Inject

class PostCheckConfigUseCase @Inject constructor(
    private val repo: SplashRepository
) {
    operator fun invoke(param: ConfigCheckRequest) = repo.postCheckConfig(param)
}