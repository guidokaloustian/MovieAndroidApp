package com.example.movieapp.clean.presentation.di

import com.example.movieapp.clean.presentation.mvvm.viewmodel.MainViewModel
import com.example.movieapp.clean.presentation.mvvm.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MoviesViewModelModule {
    val moviesViewModel = module {
        viewModel { MoviesViewModel(get()) }
    }
}