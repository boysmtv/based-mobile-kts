package com.based.kotlin.api.splash.domain.load

import com.based.kotlin.api.splash.data.repository.SplashRepository
import javax.inject.Inject

class LoadAppVersionUseCase @Inject constructor(
    private val repo: SplashRepository
) {
    operator fun invoke() = repo.appConfigLocalDataSource.loadAppVersion()
}