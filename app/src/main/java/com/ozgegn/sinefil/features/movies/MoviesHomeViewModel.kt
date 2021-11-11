package com.ozgegn.sinefil.features.movies

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.data.Result
import com.ozgegn.sinefil.data.mapper.pagingToMovieDisplayModelList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<PagingData<MovieModel>?>()
    val nowPlayingMovies: LiveData<PagingData<MovieModel>?>
        get() = _nowPlayingMovies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _movieClicked = MutableLiveData<Int>()
    val movieClicked: LiveData<Int>
        get() = _movieClicked

    fun getMovies() {

        _isLoading.value = true

        viewModelScope.launch {
            //to show loading animation
            delay(2000)
            moviesRepository.getNowPlayingMovies().map { data ->
                data?.pagingToMovieDisplayModelList()
            }.catch {
                _isLoading.value = false
                _errorMessage.value = "Can not loaded"
            }.collect {
                _isLoading.value = false
                _nowPlayingMovies.value = it
            }
        }
    }

}