package com.example.movieapp.clean.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.clean.data.database.dao.MovieDao
import com.example.movieapp.clean.data.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
