package com.ozgegn.sinefil.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.databinding.FragmentMoviesHomeBinding
import com.ozgegn.sinefil.features.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        viewModel.movieClicked.observe(viewLifecycleOwner) {
            navigateToMovieDetail(it)
        }

        val nowPlayingMoviesAdapter = MoviesPagingAdapter(MovieClickListener { movie ->
            viewModel.onMovieClicked(movie)
        })
        binding?.homeNowPlayingMoviesList?.adapter = nowPlayingMoviesAdapter
        viewModel.getMovies()
        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) { data ->
            viewLifecycleOwner.lifecycleScope.launch {
                data?.let {
                    nowPlayingMoviesAdapter.submitData(it)
                }
            }
        }

    }

    private fun navigateToMovieDetail(movieId: Int) {
        val action =
            MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMovieDetailFragment(movieId)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}