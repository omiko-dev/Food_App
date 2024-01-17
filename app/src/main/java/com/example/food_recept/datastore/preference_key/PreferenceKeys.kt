package com.example.food_recept.datastore.preference_key

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val TOKEN = stringPreferencesKey("token")
    val IS_ENTER = booleanPreferencesKey("is_enter")
}