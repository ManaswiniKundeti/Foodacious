package com.manu.foodacious.network

import com.manu.foodacious.model.collection.CollectionResponseData
import com.manu.foodacious.model.geocode.GeocodeResponseData
import com.manu.foodacious.model.restaurant.RestaurantResponseData
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

    @Headers("user-key:080aa6684f4410919d9208e8549fb979")
    @GET("collections")
    suspend fun fetchCollectionList(
        @Query("city_id")
        city_id : Int
    ) : Response<CollectionResponseData>

    @Headers("user-key:080aa6684f4410919d9208e8549fb979")
    @GET("search")
    suspend fun fetchRestaurantsByCollection(
        @Query("entity_id")
        entity_id : Int,
        @Query("entity_type")
        entity_type : String,
        @Query("collection_id")
        collection_id : Int
    ) : Response<RestaurantResponseData>

    @Headers("user-key:080aa6684f4410919d9208e8549fb979")
    @GET("geocode")
    suspend fun fetchCityId(
        @Query("lat")
        latitude : Double,
        @Query("lon")
        longitude : Double
    ) : Response<GeocodeResponseData>
}