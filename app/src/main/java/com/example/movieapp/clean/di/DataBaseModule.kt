package com.example.movieapp.clean.di

import com.example.movieapp.clean.data.database.MovieDataBaseImpl
import com.example.movieapp.clean.domain.database.MovieRepository
import org.koin.dsl.module

object DataBaseModule {

    val dataBaseModule = module {
        single<MovieRepository> { MovieDataBaseImpl(get()) }
    }
}