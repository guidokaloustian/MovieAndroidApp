package com.example.movieapp.clean.presentation.mvvm.model

import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.usecase.GetMovieListUseCase
import com.example.movieapp.clean.domain.util.CoroutineResult
import com.example.movieapp.clean.presentation.mvvm.contract.MovieContract

class MainModel(private val getMoviesUseCase: GetMovieListUseCase) : MovieContract.Model {
    override suspend fun getMovies(): CoroutineResult<List<Movie>> = getMoviesUseCase()
}
