package com.example.discovermovie.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.discovermovie.data.network.MovieApi

class SearchMovieRepository(private val movieApi: MovieApi, private val startDate:String, private val endDate:String) {

    fun getPopularMoviesByTitle(title: String?) = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { SearchMoviePagingSource(movieApi, title,startDate,endDate) }
    ).flow
}