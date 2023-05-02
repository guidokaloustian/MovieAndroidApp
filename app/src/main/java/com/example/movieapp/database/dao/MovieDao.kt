package com.example.movieapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.database.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(binEntity: MovieEntity): Long

    @Query("SELECT * FROM movies")
    fun getDBMovies(): List<MovieEntity>
}