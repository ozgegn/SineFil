package com.ozgegn.sinefil.features.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgegn.sinefil.data.MoviesRepository
import com.ozgegn.sinefil.data.ProviderModel
import com.ozgegn.sinefil.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamServicesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _providers = MutableLiveData<List<ProviderModel>>()
    val providers: LiveData<List<ProviderModel>>
        get() = _providers

    fun getAvailableProviders(region: String) {
        viewModelScope.launch {
            val result = repository.getProviders(region)
            if (result is Result.Success) {
                _providers.value = result.data ?: listOf()
            } else {
                _providers.value = listOf()
            }
        }
    }

}