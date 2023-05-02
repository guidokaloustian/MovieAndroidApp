package com.example.movieapp.database.mapper

import com.example.movieapp.database.entity.MovieEntity
import com.example.movieapp.service.model.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity =
    MovieEntity(
        id = id,
        title = title,
        image = image,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
    )

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> =
    map { entity ->
        Movie(
            id = entity.id,
            title = entity.title,
            image = entity.image,
            voteAverage = entity.voteAverage,
            releaseDate = entity.releaseDate,
        )
    }

