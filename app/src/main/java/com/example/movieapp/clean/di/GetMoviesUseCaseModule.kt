package com.example.movieapp.clean.di

import com.example.movieapp.clean.domain.usecase.GetMovieListUseCase
import com.example.movieapp.clean.domain.usecase.GetMovieListUseCaseImpl
import org.koin.dsl.module

object GetMoviesUseCaseModule {
    val getMoviesUseCaseModule = module {
        single<GetMovieListUseCase> { GetMovieListUseCaseImpl(get(), get()) }
    }
}
