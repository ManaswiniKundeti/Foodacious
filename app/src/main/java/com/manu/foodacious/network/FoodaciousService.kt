package com.manu.foodacious.network

import com.manu.foodacious.BuildConfig
import com.manu.foodacious.model.Collection
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

fun createFoodaciousService() : IFoodaciousService {

    val okHttpClient = OkHttpClient
        .Builder()
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://developers.zomato.com/api/v2.1/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(IFoodaciousService::class.java)
}

interface IFoodaciousService {

    @Headers("user-key=${BuildConfig.ZOMATO_API_KEY}")
    @GET("collections/")
    suspend fun fetchCollectionList(
        @Query("city_id")
        city_id : Int
    ) : Response<List<Collection>>
}