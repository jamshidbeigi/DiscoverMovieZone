package com.example.discovermovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.discovermovie.data.paging.MovieRepository
import com.example.discovermovie.data.model.MovieModel

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movies: LiveData<PagingData<MovieModel>> = movieRepository.getPopularMovies().asLiveData().cachedIn(viewModelScope)
}