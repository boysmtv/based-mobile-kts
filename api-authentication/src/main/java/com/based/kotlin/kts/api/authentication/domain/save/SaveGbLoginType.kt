/*
 *
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.based.kotlin.kts.api.authentication.domain.save

import com.based.kotlin.kts.api.authentication.data.repository.AuthRepository
import javax.inject.Inject

class SaveGbLoginType @Inject constructor(private val repo: AuthRepository) {
    operator fun invoke(gbLoginType: String) = repo.localDataSource.saveGbLoginType(gbLoginType)
}