package com.example.movieapp.clean.data.database

import com.example.movieapp.clean.data.database.dao.MovieDao
import com.example.movieapp.clean.data.mapper.mapToDataBaseMovie
import com.example.movieapp.clean.data.mapper.toMovieList
import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.database.MovieRepository
import com.example.movieapp.clean.domain.util.CoroutineResult

class MovieDataBaseImpl(private val movieDao: MovieDao) : MovieRepository {
    override suspend fun insertMoviesToDB(movies: List<Movie>) {
        movies.forEach { movie ->
            movieDao.insertMovie(movie.mapToDataBaseMovie())
        }
    }

    override suspend fun getDBMovies(): CoroutineResult<List<Movie>> {
        return movieDao.getDBMovies().let {
            if (it.isEmpty()) CoroutineResult.Failure(RuntimeException())
            else CoroutineResult.Success(it.toMovieList())
        }
    }
}
