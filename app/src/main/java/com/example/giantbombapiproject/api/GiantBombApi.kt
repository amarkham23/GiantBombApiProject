package com.example.giantbombapiproject.api

import com.example.giantbombapiproject.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface GiantBombApi {

    companion object {
        const val BASE_URL = "https://www.giantbomb.com/api/search/"
        const val API_KEY = BuildConfig.GIANT_BOMB_ACCESS_kEY
    }

    @GET("?api_key=$API_KEY&format=json&resources=game")
    suspend fun searchGames(
        @Query("query") query: String,
    ): GiantBombResponse
}