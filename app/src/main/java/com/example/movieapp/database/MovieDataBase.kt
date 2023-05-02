package com.example.movieapp.database

import com.example.movieapp.database.dao.MovieDao
import com.example.movieapp.database.mapper.mapToDataBaseMovie
import com.example.movieapp.database.mapper.mapToLocalMovie
import com.example.movieapp.service.model.Movie

interface MovieDataBase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}

class MovieDataBaseImpl(private val movieDao: MovieDao) : MovieDataBase {
    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach { movie ->
            movieDao.insertMovie(movie.mapToDataBaseMovie())
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDao.getDBMovies().mapToLocalMovie()
    }
}
