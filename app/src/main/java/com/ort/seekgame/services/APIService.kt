package com.ort.seekgame.services

import com.ort.seekgame.entities.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getGamesByName(@Url url : String): Response<GamesResponse>

    @GET
    suspend fun getGameDetail(@Url url : String): Response<Game>

    @GET
    suspend fun getPlatforms(@Url url : String): Response<PlatformsResponse>

    @GET
    suspend fun getPublishers(@Url url : String): Response<PublishersResponse>

    @GET
    suspend fun getDevelopers(@Url url : String): Response<DevelopersResponse>
}