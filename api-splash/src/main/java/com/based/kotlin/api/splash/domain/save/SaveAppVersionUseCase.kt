package com.based.kotlin.api.splash.domain.save

import com.based.kotlin.api.splash.data.repository.SplashRepository
import javax.inject.Inject

class SaveAppVersionUseCase @Inject constructor(
    private val repo: SplashRepository
) {
    operator fun invoke(version: String) = repo.appConfigLocalDataSource.saveAppVersion(version)
}