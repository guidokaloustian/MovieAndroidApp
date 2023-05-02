package com.example.movieapp.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.movieapp.mvvm.viewmodel.MainViewModel
import com.example.movieapp.service.model.Movie
import com.example.movieapp.util.CoroutineResult
import kotlinx.coroutines.Job

interface MainContract {

    interface Model {
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }

    interface ViewModel {
        fun getValue(): LiveData<MainViewModel.MainData>
        fun callService(): Job
    }
}
