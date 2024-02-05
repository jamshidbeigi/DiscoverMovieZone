package com.example.discovermovie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.discovermovie.model.MovieModel

class MoviePagingSource(private val movieApi: MovieApi) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: 1
            val response = movieApi.getPopularMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OWEzNzY4Mzc3Njc4NjA5ZThlNjhhNDkxZmVlMGJlOCIsInN1YiI6IjY1YmZjNTc2MDMxZGViMDE4M2YxNmVhOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cEgtZ1Gd-WTPacJCF6o-SPKiI6y0XWzXifDGe4Cs2pA", position)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.results.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }

}