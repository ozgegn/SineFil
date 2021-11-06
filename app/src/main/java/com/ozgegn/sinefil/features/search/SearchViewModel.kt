package com.ozgegn.sinefil.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgegn.sinefil.data.GenreModel
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _genreList = MutableLiveData<List<GenreModel>>()
    val genreList: LiveData<List<GenreModel>>
        get() = _genreList

    private val _results = MutableLiveData<List<MovieModel>>()
    val results: LiveData<List<MovieModel>>
        get() = _results

    fun getGenres() {
        viewModelScope.launch {
            val result = moviesRepository.getGenres()
            if (result is Result.Success) {
                _genreList.value = result.data ?: listOf()
            }
        }
    }

    fun getResults(genreId: Int) {
        viewModelScope.launch {
            val result = moviesRepository.getSearchResults(genreId)
            if (result is Result.Success) {
                _results.value = result.data ?: listOf()
            }
        }
    }
}