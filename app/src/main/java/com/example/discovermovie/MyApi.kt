package com.example.discovermovie

import com.example.discovermovie.model.MoviesResponse
import com.example.discovermovie.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("movie")
    suspend fun getPopularMovies(
        @Header("Authorization") apiKey: String,
        @Query("page") page: Int
    ): MoviesResponse


}