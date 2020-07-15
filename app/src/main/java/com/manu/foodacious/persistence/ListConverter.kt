package com.manu.foodacious.persistence

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<String>? {
        return getMoshiAdapter().fromJson(value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return getMoshiAdapter().toJson(list)
    }

    private fun getMoshiAdapter(): JsonAdapter<List<String>> {
        val moshi = Moshi.Builder().build()
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            String::class.java
        )
        return moshi.adapter(type)
    }
}