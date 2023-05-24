package com.example.movieapp.clean.data.mapper

import com.example.movieapp.clean.data.database.entity.MovieEntity
import com.example.movieapp.clean.data.service.model.MovieList
import com.example.movieapp.clean.data.service.model.Movie

fun MovieList.mapToList(): List<Movie> {
    val movieList = mutableListOf<Movie>()
    return results.map { movie ->
        Movie(
            id = movie.id,
            title = movie.title,
            image = movie.image,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
        )
    }
    return movieList
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        image = this.image,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
    )
}

fun Movie.mapToDataBaseMovie(): MovieEntity =
    MovieEntity(
        id = id,
        title = title,
        image = image,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
    )

fun List<MovieEntity>.toMovieList(): List<Movie> {
    return this.map { it.toMovie() }
}


