package com.example.seekgame.services

import com.example.seekgame.entities.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getGamesByName(@Url url : String): Response<GamesResponse>
}