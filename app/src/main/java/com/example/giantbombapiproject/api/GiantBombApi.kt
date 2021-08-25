package com.example.giantbombapiproject.api

import com.example.giantbombapiproject.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface GiantBombApi {

    companion object {
        const val BASE_URL = "https://www.giantbomb.com/api/search/"
        const val API_KEY = BuildConfig.GIANT_BOMB_ACCESS_kEY
    }

//https://www.giantbomb.com/api/search/?api_key=9d45436f87d3848d2bdcce810bacb6df57dfd134&format=json&resources=game&query=zelda
//https://www.giantbomb.com/api/search/?api_key=9d45436f87d3848d2bdcce810bacb6df57dfd134&format=json&resources=game&query=zelda

    //    @Headers("&format=json&resources=game")
    @GET("?api_key=$API_KEY&format=json&resources=game")
    suspend fun searchGames(
        @Query("query") query: String,
    ): GiantBombResponse
}