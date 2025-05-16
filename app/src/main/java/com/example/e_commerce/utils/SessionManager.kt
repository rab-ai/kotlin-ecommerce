package com.example.e_commerce.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveUserSession(email: String) {
        prefs.edit().putString("USER_EMAIL", email).apply()
        prefs.edit().putBoolean("IS_LOGGED_IN", true).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("IS_LOGGED_IN", false)
    }

    fun getUserEmail(): String? {
        return prefs.getString("USER_EMAIL", null)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
