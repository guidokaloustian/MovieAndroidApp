package com.example.movieapp.clean.domain.database

import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.util.CoroutineResult

interface MovieRepository {
    suspend fun getDBMovies(): CoroutineResult<List<Movie>>
    suspend fun insertMoviesToDB(moviesList: List<Movie>)
}
