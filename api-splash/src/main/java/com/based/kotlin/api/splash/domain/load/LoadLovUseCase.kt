package com.based.kotlin.api.splash.domain.load

import com.based.kotlin.api.splash.data.repository.SplashRepository
import javax.inject.Inject

class LoadLovUseCase @Inject constructor(
    private val repo: SplashRepository
) {
    operator fun invoke(key: String) =
        repo.lovConfigLocalDataSource.getLovByKey(key)?.sortedBy { it.sequence }
}