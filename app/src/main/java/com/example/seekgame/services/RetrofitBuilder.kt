package com.example.seekgame.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        private var apiUrl: String = "https://api.rawg.io/api/"

        fun get(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}