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
class MovieDetailFragment : Fragment() {

    private val detailViewModel: MovieDetailViewModel by viewModels()
    private var binding: FragmentMovieDetailBinding? = null
    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_detail,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this

        val movie = args.movie
        binding?.movie = movie
        binding?.executePendingBindings()
        binding?.movieDetailMotion?.transitionToEnd()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}