@file:Suppress("UNCHECKED_CAST")

package com.example.discovermovie.ui.main

import MovieAdapter
import com.example.discovermovie.viewmodel.MovieViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discovermovie.data.network.MovieApiFactory
import com.example.discovermovie.data.paging.MovieRepository
import com.example.discovermovie.data.paging.SearchMovieRepository
import com.example.discovermovie.viewmodel.SearchMovieViewModel
import com.example.discovermovie.databinding.FragmentHomeBinding
import com.example.discovermovie.data.model.MovieModel
import kotlinx.coroutines.launch

private const val START_DATE = "2024-01-01"
private const val END_DATE = "2024-01-31"

class HomeFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var searchViewModel: SearchMovieViewModel
    private lateinit var movieAdapter: PagingDataAdapter<MovieModel, MovieAdapter.MovieViewHolder>

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(requireActivity() as MainActivity)

        initialize()

        binding.searchImageBtn.setOnClickListener {
            handleSearchButton()
        }

        binding.searchEditText.addTextChangedListener {
            if (it!!.isEmpty()) {
                binding.closeBtn.visibility = View.GONE
                showAllItems()
            }
        }

        binding.closeBtn.setOnClickListener {

            showAllItems()
            binding.closeBtn.visibility = View.GONE
            binding.searchEditText.text.clear()

        }
    }

    private fun handleSearchButton() {
        if (binding.searchEditText.text.isNotEmpty()) {

            binding.closeBtn.visibility = View.VISIBLE

            searchViewModel.searchTitle(binding.searchEditText.text.toString())

            searchViewModel.movies.observe(viewLifecycleOwner) { pagingData ->
                viewLifecycleOwner.lifecycleScope.launch {
                    movieAdapter.submitData(pagingData)
                }
            }

        } else {
            binding.closeBtn.visibility = View.GONE

            viewModel.movies.observe(viewLifecycleOwner) { pagingData ->
                viewLifecycleOwner.lifecycleScope.launch {
                    movieAdapter.submitData(pagingData)
                }
            }
        }

        binding.movieRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
    }

    private fun initialize() {
        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(
                        MovieRepository(
                            MovieApiFactory.create(), START_DATE,
                            END_DATE
                        )
                    ) as T
                }
            }
        )[MovieViewModel::class.java]


        searchViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SearchMovieViewModel(
                        SearchMovieRepository(MovieApiFactory.create(), START_DATE, END_DATE),
                        null
                    ) as T
                }
            }
        )[SearchMovieViewModel::class.java]

        showAllItems()
    }

    private fun showAllItems() {
        viewModel.movies.observe(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                movieAdapter.submitData(pagingData)
            }
        }
        binding.movieRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
    }

}