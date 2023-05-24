package com.example.movieapp.clean.di

import androidx.room.Room
import com.example.movieapp.clean.data.database.MovieRoomDataBase
import org.koin.dsl.module

object RoomDBModule {
    private const val dataBase = "RoomDataBase"

    val roomDBModule = module {
        single { Room.databaseBuilder(get(), MovieRoomDataBase::class.java, dataBase).build() }
        single { get<MovieRoomDataBase>().movieDao() }
    }
}
