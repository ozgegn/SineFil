package com.ozgegn.sinefil.features.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.databinding.FragmentWatchlistBinding
import com.ozgegn.sinefil.features.MovieClickListener
import com.ozgegn.sinefil.features.movies.HomeListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchListFragment: Fragment() {

    private var binding: FragmentWatchlistBinding? = null
    private val viewModel: WatchListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        val adapter = HomeListAdapter(MovieClickListener { movie ->
            val action = WatchListFragmentDirections.actionWatchListFragmentToMovieDetailFragment(movie)
            findNavController().navigate(action)
        })
        binding?.watchList?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}