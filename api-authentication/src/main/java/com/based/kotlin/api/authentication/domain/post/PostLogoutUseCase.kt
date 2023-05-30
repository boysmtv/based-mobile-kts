package com.based.kotlin.api.authentication.domain.post

import com.based.kotlin.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class PostLogoutUseCase @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke() = repo.postLogout()
}