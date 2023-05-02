package com.example.movieapp.mvvm.model

import com.example.movieapp.database.MovieDataBase
import com.example.movieapp.mvvm.contract.MainContract
import com.example.movieapp.service.MovieService
import com.example.movieapp.service.model.Movie
import com.example.movieapp.util.CoroutineResult

class MainModel(
    private val service: MovieService,
    private val database: MovieDataBase,
) : MainContract.Model {

    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                database.insertMovies(movies.data.results)
                CoroutineResult.Success(database.getAllMovies())
            }

            is CoroutineResult.Failure -> {
                CoroutineResult.Success(database.getAllMovies())
            }
        }
    }
}
