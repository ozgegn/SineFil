package com.ozgegn.sinefil.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.databinding.FragmentMoviesHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesHomeFragment : Fragment() {

    private val viewModel: MoviesHomeViewModel by viewModels()
    private var binding: FragmentMoviesHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_home, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this

        val popularMoviesAdapter = HomeListAdapter(MovieClickListener { movie ->
            val action = MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMovieDetailFragment(movie.id)
            findNavController().navigate(action)
        })

        val nowPlayingMoviesAdapter = HomeListAdapter(MovieClickListener { movie ->
            val action = MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMovieDetailFragment(movie.id)
            findNavController().navigate(action)
        })

        val topRatedMoviesAdapter = HomeListAdapter(MovieClickListener { movie ->
            val action = MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMovieDetailFragment(movie.id)
            findNavController().navigate(action)
        })
        binding?.homePopularMoviesList?.isNestedScrollingEnabled = false
        binding?.homeNowPlayingMoviesList?.isNestedScrollingEnabled = false
        binding?.homeTopRatedMoviesList?.isNestedScrollingEnabled = false

        binding?.homePopularMoviesList?.adapter = popularMoviesAdapter
        binding?.homeNowPlayingMoviesList?.adapter = nowPlayingMoviesAdapter
        binding?.homeTopRatedMoviesList?.adapter = topRatedMoviesAdapter
        viewModel.getMovies(1)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}