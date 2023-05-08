package com.based.kotlin.kts.api.splash.data.local

import com.based.kotlin.kts.core.common.util.security.CorePrefManager
import com.based.kotlin.kts.core.entity.util.LanguagePack
import com.based.kotlin.kts.utilities.constants.PreferenceConstants
import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class SplashLanguagePackCache @Inject constructor(
    private val prefManager: CorePrefManager,
    private val adapter: JsonAdapter<LanguagePack>
) {

    @Synchronized
    fun get(): LanguagePack? =
        prefManager.getString(PreferenceConstants.Language.PREF_KEY_LANGUAGE_PACK)?.let {
            adapter.fromJson(it)
        }

    fun set(languagePack: LanguagePack) {
        val data = adapter.toJson(languagePack)
        prefManager.setString(PreferenceConstants.Language.PREF_KEY_LANGUAGE_PACK, data)
    }

    fun clear() {
        prefManager.remove(PreferenceConstants.Language.PREF_KEY_LANGUAGE_PACK)
    }
}