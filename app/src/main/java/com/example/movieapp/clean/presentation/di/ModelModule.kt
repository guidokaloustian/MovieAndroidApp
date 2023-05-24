package com.example.movieapp.clean.presentation.di

import com.example.movieapp.clean.presentation.mvvm.contract.MovieContract
import com.example.movieapp.clean.presentation.mvvm.model.MainModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module {
        single<MovieContract.Model> { MainModel(get()) }
    }
}