package com.manu.foodacious.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(context : Context) {

    private val sharedPreferenceFile = "mySharedPreferenceFile"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPreferenceFile,
        Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int) = sharedPreferences.getInt(key, defaultValue)

    fun clearInt(key : String){
        sharedPreferences.edit().remove(key).apply();
    }
}