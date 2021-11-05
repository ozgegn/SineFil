package com.ozgegn.sinefil.features.movieDetail

import androidx.databinding.PropertyChangeRegistry
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
class MovieDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {


    private val _movie = MutableLiveData<MovieModel?>()
    val movie: LiveData<MovieModel?>
        get() = _movie

    fun getMovie(id: Int) {

        viewModelScope.launch {
            val movie = moviesRepository.getMovie(id)
            if (movie is Result.Success) {
                _movie.value = movie.data
            }
            else {
                _movie.value = null
            }
        }
    }

}