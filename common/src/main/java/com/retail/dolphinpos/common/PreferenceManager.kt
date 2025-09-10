package com.retail.dolphinpos.common

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit

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
}