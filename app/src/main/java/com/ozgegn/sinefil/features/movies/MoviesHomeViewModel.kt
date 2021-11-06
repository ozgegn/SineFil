package com.ozgegn.sinefil.features.movies

import androidx.databinding.ObservableBoolean
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

    private val _nowPlayingMovies = MutableLiveData<List<MovieModel>>()
    val nowPlayingMovies: LiveData<List<MovieModel>>
        get() = _nowPlayingMovies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    var isLoading = ObservableBoolean()

    private val _movieClicked = MutableLiveData<Int>()
    val movieClicked: LiveData<Int>
        get() = _movieClicked


    fun getMovies(page: Int) {

        isLoading.set(true)

        viewModelScope.launch {
            val nowPlayingMovies = moviesRepository.getNowPlayingMovies(1)
            if (nowPlayingMovies is Result.Success) {
                _nowPlayingMovies.value = nowPlayingMovies.data ?: listOf()
            } else {
                _errorMessage.value = (nowPlayingMovies as Result.Error).exception.message
            }
        }
    }

    fun onMovieClicked(movieModel: MovieModel) {
        viewModelScope.launch {
            moviesRepository.saveMovie(movieModel)
            _movieClicked.value = movieModel.id
        }
    }

}