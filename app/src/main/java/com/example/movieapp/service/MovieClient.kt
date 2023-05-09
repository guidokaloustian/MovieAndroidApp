package com.example.movieapp.service

import com.example.movieapp.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "82c3129d2400117c1cdda32b9415e0ca"

interface MovieClient {
    @Headers("api_key: $API_KEY")
    @GET("3/movie/popular")
    fun getData(@Query("api_key")apiKey: String = API_KEY): Call<MovieList>
}
