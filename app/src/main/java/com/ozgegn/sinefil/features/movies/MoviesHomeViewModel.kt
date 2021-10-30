package com.ozgegn.sinefil.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.features.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _popularMovies = MutableLiveData<UIState<List<MovieModel>>>()
    val popularMovies: LiveData<UIState<List<MovieModel>>>
        get() = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<UIState<List<MovieModel>>>()
    val nowPlayingMovies: LiveData<UIState<List<MovieModel>>>
        get() = _nowPlayingMovies

    private val _topRatedMovies = MutableLiveData<UIState<List<MovieModel>>>()
    val topRatedMovies: LiveData<UIState<List<MovieModel>>>
        get() = _topRatedMovies

    fun getMovies(page: Int) {

        _popularMovies.value = UIState.Loading
        _nowPlayingMovies.value = UIState.Loading
        _topRatedMovies.value = UIState.Loading

        viewModelScope.launch {

        }
    }

}