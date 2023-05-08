package com.based.kotlin.kts.api.authentication.domain.save

import com.based.kotlin.kts.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class SaveRefreshTokenTimestampUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(value: Long) = repo.localDataSource.saveRefreshTokenTimestamp(value)
}