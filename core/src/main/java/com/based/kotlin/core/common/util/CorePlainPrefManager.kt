/*
 * Copyright © 2020 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.based.kotlin.core.common.util

import android.app.Application
import android.content.Context
import com.based.kotlin.core.BuildConfig

class CorePlainPrefManager(app: Application) {

    private val preferences = app.getSharedPreferences(BuildConfig.PREFERENCE, Context.MODE_PRIVATE)

    @Synchronized
    fun setString(key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    @Synchronized
    fun setStringSet(key: String, value: Set<String>) {
        preferences.edit().putStringSet(key, value).apply()
    }

    fun addToStringSet(key: String, value: String) = getStringSet(key).takeIf { it.isNotEmpty() }
        ?.let { preferences.edit().putStringSet(key, it + value).apply() }
        ?: preferences.edit().putStringSet(key, setOf(value)).apply()

    fun setInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun setLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun setFloat(key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }

    @Synchronized
    fun setBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    @Synchronized
    fun getString(key: String): String? = preferences.getString(key, null)

    fun getStringSet(key: String): Set<String> = preferences.getStringSet(key, setOf()) ?: setOf()

    fun getInt(key: String): Int = preferences.getInt(key, 0)

    fun getLong(key: String): Long = preferences.getLong(key, 0L)

    fun getFloat(key: String): Float = preferences.getFloat(key, 0F)

    @Synchronized
    fun getBoolean(key: String): Boolean = preferences.getBoolean(key, false)

    fun isContain(key: String): Boolean = preferences.contains(key)

    @Synchronized
    fun remove(key: String) = preferences.edit().remove(key).commit()

    @Synchronized
    fun clear() = preferences.edit().clear().commit()
}