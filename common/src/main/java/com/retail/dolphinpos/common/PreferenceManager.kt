package com.retail.dolphinpos.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("dolphin_prefs", Context.MODE_PRIVATE)

    fun setRegister(value: Boolean) {
        prefs.edit { putBoolean(Constants.SET_REGISTER, value) }
    }

    fun getRegister(defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(Constants.SET_REGISTER, defaultValue)
    }

    fun setLogin(value: Boolean) {
        prefs.edit { putBoolean(Constants.IS_LOGIN, value) }
    }

    fun isLogin(defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(Constants.IS_LOGIN, defaultValue)
    }

    fun setAccessToken(value: String) {
        prefs.edit { putString(Constants.ACCESS_TOKEN, value) }
    }

    fun getAccessToken(defaultValue: String = ""): String {
        return prefs.getString(Constants.ACCESS_TOKEN, defaultValue) ?: defaultValue
    }

    fun setRefreshToken(value: String) {
        prefs.edit { putString(Constants.REFRESH_TOKEN, value) }
    }

    fun getRefreshToken(defaultValue: String = ""): String {
        return prefs.getString(Constants.REFRESH_TOKEN, defaultValue) ?: defaultValue
    }

    fun setUserID(value: Int) {
        prefs.edit { putInt(Constants.USER_ID, value) }
    }

    fun getUserID(defaultValue: Int = 0): Int {
        return prefs.getInt(Constants.USER_ID, defaultValue)
    }

    fun setUsername(value: String) {
        prefs.edit { putString(Constants.USERNAME, value) }
    }

    fun getUsername(defaultValue: String = ""): String {
        return prefs.getString(Constants.USERNAME, defaultValue) ?: defaultValue
    }

}