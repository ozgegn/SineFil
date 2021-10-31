package com.ozgegn.sinefil.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<MovieModel>>()
    val popularMovies: LiveData<List<MovieModel>>
        get() = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<List<MovieModel>>()
    val nowPlayingMovies: LiveData<List<MovieModel>>
        get() = _nowPlayingMovies

    private val _topRatedMovies = MutableLiveData<List<MovieModel>>()
    val topRatedMovies: LiveData<List<MovieModel>>
        get() = _topRatedMovies

    fun getMovies(page: Int) {

        viewModelScope.launch {
            val popularMovies = moviesRepository.getPopularMovies(1)
            val nowPlayingMovies = moviesRepository.getNowPlayingMovies(1)
            val topRatedMovies = moviesRepository.getTopRatedMovies(1)

            if (popularMovies is Result.Success) {
                _popularMovies.value = popularMovies.data ?: listOf()
            }
        }
    }

}