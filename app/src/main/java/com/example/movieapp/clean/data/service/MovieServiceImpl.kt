package com.example.movieapp.clean.data.service

import com.example.movieapp.clean.data.mapper.mapToList
import com.example.movieapp.clean.data.service.api.MovieClient
import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.service.MovieService
import com.example.movieapp.clean.domain.util.CoroutineResult

class MovieServiceImpl(private val requestGenerator: MovieRequestGenerator) : MovieService {
    override suspend fun getMovieList(): CoroutineResult<List<Movie>> {
        try {
            val response = requestGenerator.createService(MovieClient::class.java).getAllMovies().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.mapToList())
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
    }
}
