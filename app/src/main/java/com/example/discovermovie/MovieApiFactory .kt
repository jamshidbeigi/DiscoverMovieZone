package com.example.discovermovie

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiFactory  {

    private const val BASE_URL = "https://api.themoviedb.org/3/discover/"

    fun create(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}