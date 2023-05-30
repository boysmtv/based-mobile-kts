package com.based.kotlin.api.authentication.domain.load

import com.based.kotlin.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class LoadOtpDataUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.localDataSource.loadOptResponse()
}