package com.example.movieapp.clean.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.util.CoroutineResult
import com.example.movieapp.clean.presentation.mvvm.contract.MovieContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val model: MovieContract.Model) : ViewModel() {

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    fun getValue(): LiveData<MainData> = mutableLiveData

    fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getMovies() }.let { result: CoroutineResult<List<Movie>> ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MainData(Status.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MainData(Status.ERROR, exception = result.exception)
                }
            }
        }
    }

    data class MainData(
        val status: Status,
        val movies: List<Movie> = emptyList(),
        val exception: Exception? = null,
    )

    enum class Status {
        SHOW_INFO,
        ERROR,
    }
}
