package com.example.movieapp.clean.presentation.mvvm.contract

import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.util.CoroutineResult

class MovieContract {
    interface Model {
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }
}
