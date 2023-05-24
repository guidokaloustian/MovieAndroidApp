package com.example.movieapp.clean.domain.service

import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.util.CoroutineResult

interface MovieService {
    suspend fun getMovieList(): CoroutineResult<List<Movie>>
}
