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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
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

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    var isLoading = ObservableBoolean()


    fun getMovies(page: Int) {

        isLoading.set(true)

        viewModelScope.launch {
            supervisorScope {
                val popularMovies = async { moviesRepository.getPopularMovies(1) }.await()
                val nowPlayingMovies = async { moviesRepository.getNowPlayingMovies(1) }.await()
                val topRatedMovies = async { moviesRepository.getTopRatedMovies(1) }.await()

                if (popularMovies is Result.Success) {
                    _popularMovies.value = popularMovies.data ?: listOf()
                } else {
                    _errorMessage.value = (popularMovies as Result.Error).exception.message
                }

                if (nowPlayingMovies is Result.Success) {
                    _nowPlayingMovies.value = nowPlayingMovies.data ?: listOf()
                }

                if (topRatedMovies is Result.Success) {
                    _topRatedMovies.value = topRatedMovies.data ?: listOf()
                }
            }
        }
    }

}