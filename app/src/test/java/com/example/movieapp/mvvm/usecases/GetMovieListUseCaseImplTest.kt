package com.example.movieapp.mvvm.usecases

import com.example.movieapp.clean.domain.database.MovieRepository
import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.service.MovieService
import com.example.movieapp.clean.domain.usecase.GetMovieListUseCase
import com.example.movieapp.clean.domain.usecase.GetMovieListUseCaseImpl
import com.example.movieapp.clean.domain.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMovieListUseCaseImplTest {
    @MockK
    private lateinit var movieService: MovieService

    @MockK
    private lateinit var dataBase: MovieRepository

    @MockK
    private lateinit var movieList: List<Movie>

    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        getMovieListUseCase = GetMovieListUseCaseImpl(movieService, dataBase)
    }

    @Test
    fun `when the service success, it should return a movie list`() {
        coEvery { movieService.getMovieList() } returns CoroutineResult.Success(movieList)
        coEvery { dataBase.getDBMovies() } returns CoroutineResult.Success(movieList)

        val result = runBlocking { getMovieListUseCase() }

        coVerify { dataBase.insertMoviesToDB(movieList) }
        coVerify { dataBase.getDBMovies() }

        assertEquals(movieList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `when the service fails, it should throw an exception`() {
        coEvery { movieService.getMovieList() }.returns(CoroutineResult.Failure(Exception(errorMessage)))
        coEvery { dataBase.getDBMovies() }.returns(CoroutineResult.Failure(Exception(errorMessage)))

        val result = runBlocking { getMovieListUseCase() }

        coVerify { dataBase.getDBMovies() }

        assertEquals(errorMessage, ((result as CoroutineResult.Failure).exception.message))
    }

    companion object {
        private const val errorMessage = "ERROR"
    }
}