package com.example.namegame.database.shared_preference

import android.content.Context

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Preference(context: Context) {
    private val darkMode: Boolean = false
    private val theme = context.getSharedPreferences(darkMode.toString(), Context.MODE_PRIVATE)!!

    fun getTheme(): Boolean {
        return theme.getBoolean(darkMode.toString(), false)
    }

    fun setTheme(isEnabled: Boolean) {
        val editor = theme.edit()
        editor.putBoolean(darkMode.toString(), isEnabled)
        editor.apply()
    }
}
