package com.example.movieapp.clean.data.service.api

import com.example.movieapp.clean.data.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "7858bb40ea941a0512348fb43fd59fd8"

interface MovieClient {
    @Headers("api_key: $API_KEY")
    @GET("3/movie/popular")
    fun getAllMovies(@Query("api_key")apiKey: String = API_KEY): Call<MovieList>
}