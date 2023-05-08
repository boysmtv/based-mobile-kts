package com.based.kotlin.kts.api.authentication.domain.load

import com.based.kotlin.kts.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class LoadRefreshTokenTimestampUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke() = repo.localDataSource.loadRefreshTokenTimestamp()
}