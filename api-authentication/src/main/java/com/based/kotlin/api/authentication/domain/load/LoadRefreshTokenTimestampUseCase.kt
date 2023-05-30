package com.based.kotlin.api.authentication.domain.load

import com.based.kotlin.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class LoadRefreshTokenTimestampUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke() = repo.localDataSource.loadRefreshTokenTimestamp()
}