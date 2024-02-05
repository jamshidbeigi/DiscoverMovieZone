import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.discovermovie.MovieRepository
import com.example.discovermovie.model.MovieModel

import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movies: LiveData<PagingData<MovieModel>> = movieRepository.getPopularMovies().asLiveData().cachedIn(viewModelScope)
}