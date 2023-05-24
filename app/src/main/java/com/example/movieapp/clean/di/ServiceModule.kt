package com.example.movieapp.clean.di

import com.example.movieapp.clean.data.service.MovieServiceImpl
import com.example.movieapp.clean.domain.service.MovieService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        single<MovieService> { MovieServiceImpl(get()) }
    }
}