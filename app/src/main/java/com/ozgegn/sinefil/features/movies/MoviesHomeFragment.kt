package com.ozgegn.sinefil.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        val adapter = HomeListAdapter(MovieClickListener { movie ->

        })
        binding?.homePopularMoviesList?.adapter = adapter
        binding?.homeNowPlayingMoviesList?.adapter = adapter
        binding?.homeTopRatedMoviesList?.adapter = adapter
        viewModel.getMovies(1)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}