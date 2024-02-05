package com.example.discovermovie.data.network

import com.example.discovermovie.data.model.MoviesResponse
import com.example.discovermovie.data.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Header("Authorization") apiKey: String,
        @Query("page") page: Int,
        @Query("primary_release_date.gte") startDate:String,
        @Query("primary_release_date.lte") endDate: String?
    ): MoviesResponse

    @GET("search/movie")
    suspend fun getPopularMoviesBaseTitle(
        @Header("Authorization") apiKey: String,
        @Query("page") page: Int,
        @Query("query") title: String?,
        @Query("primary_release_date.gte") startDate:String,
        @Query("primary_release_date.lte") endDate: String?
    ): MoviesResponse


}