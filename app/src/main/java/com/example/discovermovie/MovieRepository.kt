package com.example.discovermovie

import androidx.paging.Pager
import androidx.paging.PagingConfig

class MovieRepository(private val movieApi: MovieApi) {

        fun getPopularMovies() = Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(movieApi) }
        ).flow
    }