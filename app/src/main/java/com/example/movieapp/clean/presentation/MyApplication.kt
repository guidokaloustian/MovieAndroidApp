package com.example.movieapp.clean.presentation

import android.app.Application
import com.example.movieapp.clean.di.DataBaseModule.dataBaseModule
import com.example.movieapp.clean.di.GetMoviesUseCaseModule.getMoviesUseCaseModule
import com.example.movieapp.clean.di.RequestGeneratorModule.requestGeneratorModule
import com.example.movieapp.clean.di.RoomDBModule.roomDBModule
import com.example.movieapp.clean.di.ServiceModule.serviceModule
import com.example.movieapp.clean.presentation.di.MainViewModelModule.mainViewModel
import com.example.movieapp.clean.presentation.di.ModelModule.modelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    mainViewModel,
                    dataBaseModule,
                    getMoviesUseCaseModule,
                    serviceModule,
                    roomDBModule,
                    requestGeneratorModule,
                    modelModule,
                ),
            )
        }
    }
}
