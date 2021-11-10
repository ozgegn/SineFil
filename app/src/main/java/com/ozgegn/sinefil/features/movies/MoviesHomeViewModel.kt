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

    var isLoading = ObservableBoolean()

    private val _movieClicked = MutableLiveData<Int>()
    val movieClicked: LiveData<Int>
        get() = _movieClicked


    fun getMovies() {

        isLoading.set(true)

        viewModelScope.launch {
            moviesRepository.getNowPlayingMovies().map { data ->
                data?.pagingToMovieDisplayModelList()
            }.catch {
                _errorMessage.value = "Can not loaded"
            }.collect {
                _nowPlayingMovies.value = it
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