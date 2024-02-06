package com.example.discovermovie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.discovermovie.data.model.MovieModel
import com.example.discovermovie.data.network.MovieApi

class MoviePagingSource(private val movieApi: MovieApi, private val startDate:String, private val endDate:String) : PagingSource<Int, MovieModel>() {

    // show all movies paging source

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: 1
            val response = movieApi.getPopularMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OWEzNzY4Mzc3Njc4NjA5ZThlNjhhNDkxZmVlMGJlOCIsInN1YiI6IjY1YmZjNTc2MDMxZGViMDE4M2YxNmVhOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cEgtZ1Gd-WTPacJCF6o-SPKiI6y0XWzXifDGe4Cs2pA"
                , position, startDate,endDate)
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