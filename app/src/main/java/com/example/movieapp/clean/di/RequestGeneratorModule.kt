package com.example.movieapp.clean.di

import com.example.movieapp.clean.data.service.MovieRequestGenerator
import org.koin.dsl.module

object RequestGeneratorModule {
    val requestGeneratorModule = module {
        single { MovieRequestGenerator() }
    }
}