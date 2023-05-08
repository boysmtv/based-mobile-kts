package com.based.kotlin.kts.api.authentication.domain.get

import com.based.kotlin.kts.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class GetAuthRefreshTokenIdentityUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke() = repo.getRefreshTokenIdentity()
}