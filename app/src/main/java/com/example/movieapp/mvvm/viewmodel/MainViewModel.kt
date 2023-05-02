package com.example.movieapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.mvvm.contract.MainContract
import com.example.movieapp.service.model.Movie
import com.example.movieapp.util.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val model: MainContract.Model) : ViewModel(), MainContract.ViewModel {

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    override fun getValue(): LiveData<MainData> = mutableLiveData

    override fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getMovies() }.let { result: CoroutineResult<List<Movie>> ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MainData(Status.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure -> {
                    // TODO: see what should response
                }
            }
        }
    }

    data class MainData(
        val status: Status,
        val exercises: List<Movie>,
    )

    enum class Status {
        SHOW_INFO,
    }
}
