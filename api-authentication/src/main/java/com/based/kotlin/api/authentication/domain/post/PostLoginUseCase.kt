/*
 * Copyright © 2020 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.authentication.domain.post

import com.based.kotlin.api.authentication.data.repository.AuthRepository
import com.based.kotlin.core.common.util.security.rsa.RsaEncryptor
import com.based.kotlin.core.entity.auth.AuthRequest
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val repo: AuthRepository,
    private val rsa: RsaEncryptor
) {
    operator fun invoke(request: AuthRequest) = repo.postLogin(
        AuthRequest(
            userId = request.userId,
            password = rsa.encryptString(request.password.toString())
        )
    )
}