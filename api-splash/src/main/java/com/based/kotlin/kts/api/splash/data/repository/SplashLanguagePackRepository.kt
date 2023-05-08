package com.based.kotlin.kts.api.splash.data.repository

import androidx.annotation.VisibleForTesting
import com.i18next.android.I18Next
import com.i18next.android.Operation
import com.based.kotlin.kts.api.splash.data.local.SplashLanguagePackCache
import com.based.kotlin.kts.api.splash.data.local.SplashLanguagePackResource
import com.based.kotlin.kts.core.common.entity.Result
import com.based.kotlin.kts.core.common.util.orReplaceWith
import com.based.kotlin.kts.core.data.CoroutineDispatcherProvider
import com.based.kotlin.kts.core.data.LanguagePackProvider
import com.based.kotlin.kts.core.data.SupportedLanguage
import com.based.kotlin.kts.core.data.resultFlow
import com.based.kotlin.kts.core.entity.util.LanguagePack
import com.based.kotlin.kts.core.common.util.security.CorePrefManager
import com.based.kotlin.kts.utilities.constants.Constants.LANGUAGE_ID_ID
import com.based.kotlin.kts.utilities.constants.PreferenceConstants
import com.based.kotlin.kts.utilities.constants.RespondConstants.LanguagePackCode.CODE_200
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.jetbrains.annotations.TestOnly
import org.json.JSONException
import timber.log.Timber
import javax.inject.Inject

class SplashLanguagePackRepository @Inject constructor(
    private val resource: SplashLanguagePackResource,
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatcherProvider,
    private val cache: SplashLanguagePackCache,
    private val prefManager: CorePrefManager
) : LanguagePackProvider {

    private lateinit var i18Next: I18Next

    override fun get(key: String, operation: Operation?): String? = i18Next.t(key, operation)

    override fun get(key: String, language: SupportedLanguage, operation: Operation?): String? {
        val prevLang = i18Next.options.language
        i18Next.options.language = language.toString()
        val translation = i18Next.t(key, operation)
        i18Next.options.language = prevLang
        return translation
    }

    override fun setLanguage(lang: String) {
        i18Next.options.language = lang.lowercase()
    }

    override fun load(defaultLanguage: SupportedLanguage) {
        i18Next = I18Next()
        i18Next.options.language = defaultLanguage.toString()

        val languagePack = this.loadFromCache() ?: this.loadFromBundledResource()
        if (languagePack != null) {
            saveLanguagePackId(languagePack.languagePackId)
            this.loadLanguagePackIntoI18Next(i18Next, languagePack)
        }
    }

    private fun loadFromCache() = try {
        cache.get()
    } catch (e: JsonDataException) {
        cache.clear()
        Timber.e(e)
        null
    }

    private fun loadFromBundledResource() = try {
        resource.get()
    } catch (e: JsonDataException) {
        Timber.e(e)
        null
    }

    private fun loadLanguagePackIntoI18Next(instance: I18Next, languagePack: LanguagePack) {
        languagePack.content?.forEach { (key, _) ->
            try {
                val lang = SupportedLanguage.fromIso639Main(key)?.toString() ?: return@forEach
                instance.loader().apply {
                    lang(lang)
                    from(languagePack.forLanguageAsJson(key, moshi))
                    load()
                }
            } catch (e: JSONException) {
                Timber.e(e)
            }
        }
    }

    fun getLanguagePack() = resultFlow(
        networkCall = {
            Result(Result.Status.SUCCESS, LanguagePack(), null, null, CODE_200, CODE_200)
        },
        saveCallResult = { languagePack ->
            languagePack.content?.let {
                cache.set(languagePack)
                saveLanguagePackId(languagePack.languagePackId)
            }
        },
        dispatcher = dispatcher
    )

    fun getDefaultLanguage() =
        prefManager.getString(PreferenceConstants.Language.SHARED_PREF_LANGUAGE)
            .orReplaceWith(LANGUAGE_ID_ID)

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun initInternationalization(mockI18Next: I18Next) {
        this.i18Next = mockI18Next
    }

    //region LOAD
    fun loadLanguagePackId() =
        prefManager.getString(PreferenceConstants.Language.PREF_KEY_LANGUAGE_PACK_ID)
            .orReplaceWith(LANGUAGE_ID_ID)

    fun loadLanguagePack() = cache.get()
    //endregion

    //region SAVE
    private fun saveLanguagePackId(languagePackId: String?) =
        prefManager.setString(
            PreferenceConstants.Language.PREF_KEY_LANGUAGE_PACK_ID,
            languagePackId.orReplaceWith(LANGUAGE_ID_ID)
        )

    fun saveLanguagePack(languagePack: LanguagePack) = cache.set(languagePack)
}