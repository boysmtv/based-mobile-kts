package com.based.kotlin.api.authentication.domain.save

import com.based.kotlin.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class SaveGbLoginType @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(gbLoginType: String) = repo.localDataSource.saveGbLoginType(gbLoginType)
}