package com.example.discovermovie.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.discovermovie.data.network.MovieApi

class MovieRepository(private val movieApi: MovieApi, private val startDate:String, private val endDate:String) {

    // show all movies paging repository

    fun getPopularMovies() = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { MoviePagingSource(movieApi,startDate,endDate) }
    ).flow

}