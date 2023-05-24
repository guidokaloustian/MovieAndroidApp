package com.example.movieapp.clean.presentation.di

import com.example.movieapp.clean.presentation.mvvm.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainViewModelModule {
    val mainViewModel = module {
        viewModel { MoviesViewModel(get()) }
    }
}
