package com.example.discovermovie

import MovieAdapter
import MovieViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discovermovie.databinding.FragmentHomeBinding
import com.example.discovermovie.model.MovieModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: PagingDataAdapter<MovieModel, MovieAdapter.MovieViewHolder>

    private val movies by lazy {
        viewModel.movies
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(MovieRepository(MovieApiFactory.create())) as T
                }
            }
        ).get(MovieViewModel::class.java)


        movieAdapter = MovieAdapter(requireActivity() as MainActivity)




        viewModel.movies.observe(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                movieAdapter.submitData(pagingData)
            }
        }

        binding.movieRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }




        return binding.root
    }

}