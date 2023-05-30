package com.based.kotlin.core.data

import com.i18next.android.Operation

interface LanguagePackProvider {

    fun get(key: String, operation: Operation? = null): String?

    fun get(key: String, language: SupportedLanguage, operation: Operation? = null): String?

    fun setLanguage(lang: String)

    fun load(defaultLanguage: SupportedLanguage)
}