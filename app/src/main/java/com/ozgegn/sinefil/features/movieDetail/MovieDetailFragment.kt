package com.ozgegn.sinefil.features.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment: Fragment() {

    private val detailViewModel: MovieDetailViewModel by viewModels()
    private var binding: FragmentMovieDetailBinding? = null
    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(inflater, R.layout.fragment_movie_detail, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId

        with(binding) {
            this?.lifecycleOwner = this@MovieDetailFragment
        }

        detailViewModel.movie.observe(viewLifecycleOwner) {
            binding?.movie = it
            binding?.executePendingBindings()
        }

        detailViewModel.getMovie(movieId)

    }

}