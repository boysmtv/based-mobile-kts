package com.based.kotlin.kts.api.authentication.domain.save

import com.based.kotlin.kts.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class SaveUserTypeUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(value: String) = repo.localDataSource.saveUserType(value)
}