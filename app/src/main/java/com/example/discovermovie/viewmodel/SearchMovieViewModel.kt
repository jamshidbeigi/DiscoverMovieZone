package com.example.discovermovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.discovermovie.data.paging.SearchMovieRepository
import com.example.discovermovie.data.model.MovieModel

class SearchMovieViewModel(private var searchMovieRepository: SearchMovieRepository, private var title: String?) : ViewModel() {

    var movies: LiveData<PagingData<MovieModel>> = searchMovieRepository.getPopularMoviesByTitle(null).asLiveData().cachedIn(viewModelScope)

    fun searchTitle(title:String?){
        movies = searchMovieRepository.getPopularMoviesByTitle(title).asLiveData().cachedIn(viewModelScope)

    }
}