package com.example.seekgame.services

import com.example.seekgame.entities.DevelopersResponse
import com.example.seekgame.entities.GamesResponse
import com.example.seekgame.entities.PlatformsResponse
import com.example.seekgame.entities.PublishersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getGamesByName(@Url url : String): Response<GamesResponse>

    @GET
    suspend fun getPlatforms(@Url url : String): Response<PlatformsResponse>

    @GET
    suspend fun getPublishers(@Url url : String): Response<PublishersResponse>

    @GET
    suspend fun getDevelopers(@Url url : String): Response<DevelopersResponse>
}