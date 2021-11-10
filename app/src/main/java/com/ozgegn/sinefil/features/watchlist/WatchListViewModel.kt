package com.ozgegn.sinefil.features.watchlist

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
class WatchListViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _watchList = MutableLiveData<List<MovieModel>>()
    val watchList: LiveData<List<MovieModel>>
        get() = _watchList

    private val _noData = MutableLiveData<Boolean>()
    val noData: LiveData<Boolean>
        get() = _noData

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            val result = moviesRepository.getWatchList()
            if (result is Result.Success) {
                if (result.data.isNullOrEmpty())
                    _noData.value = true
                else {
                    _watchList.value = result.data!!
                    _noData.value = false
                }
            } else {
                _noData.value = true
            }
        }
    }

}