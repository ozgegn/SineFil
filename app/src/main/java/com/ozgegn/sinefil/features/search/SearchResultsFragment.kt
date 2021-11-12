package com.ozgegn.sinefil.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.databinding.FragmentSearchResultBinding
import com.ozgegn.sinefil.features.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultsFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var binding: FragmentSearchResultBinding? = null
    private val args: SearchResultsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        val genreId = args.genreId
        viewModel.getResults(genreId)

        val adapter = HomeListAdapter(MovieClickListener { movie ->
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToMovieDetailFragment(movie)
            findNavController().navigate(action)
        })
        binding?.searchMovieResult?.adapter = adapter
    }
}