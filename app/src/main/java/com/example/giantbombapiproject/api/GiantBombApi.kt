package com.example.giantbombapiproject.api

import com.example.giantbombapiproject.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GiantBombApi {

    companion object {
        const val BASE_URL = "http://www.giantbomb.com/api/"
        const val KEY_ID = BuildConfig.GIANT_BOMB_ACCESS_kEY
    }

    @Headers("Authorization: Client-ID $KEY_ID")
    @GET("search")
    suspend fun searchGames(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): GiantBombResponse
}