package com.example.movieapp.clean.domain.usecase

import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.database.MovieRepository
import com.example.movieapp.clean.domain.service.MovieService
import com.example.movieapp.clean.domain.util.CoroutineResult

interface GetMovieListUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}
class GetMovieListUseCaseImpl(private val movieService: MovieService, private val movieRepository: MovieRepository) : GetMovieListUseCase {
    override suspend fun invoke(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = movieService.getMovieList()) {
            is CoroutineResult.Success -> {
                movieRepository.insertMoviesToDB(serviceResult.data)
                movieRepository.getDBMovies()
            }
            is CoroutineResult.Failure -> movieRepository.getDBMovies()
        } }
}
