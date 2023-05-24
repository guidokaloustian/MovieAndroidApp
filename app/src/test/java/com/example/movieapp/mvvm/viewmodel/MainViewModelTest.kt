package com.example.movieapp.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.clean.presentation.mvvm.viewmodel.MoviesViewModel
import com.example.movieapp.clean.data.service.model.Movie
import com.example.movieapp.clean.domain.util.CoroutineResult
import com.example.movieapp.clean.presentation.mvvm.contract.MovieContract
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @MockK
    private lateinit var model: MovieContract.Model

    @MockK
    private lateinit var movieList: List<Movie>

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MoviesViewModel(model)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when the service call is successful, the data status should be 'SHOW_INFO'`() {
        coEvery { model.getMovies() } returns CoroutineResult.Success(movieList)

        runBlocking { viewModel.callService().join() }

        assertEquals(movieList, viewModel.getValue().value?.movies)
        assertEquals(MoviesViewModel.Status.SHOW_INFO, viewModel.getValue().value?.status)
    }

    @Test
    fun `when the service call fails, the data status should be 'ERROR'`() {
        coEvery { model.getMovies() } returns CoroutineResult.Failure(Exception())

        runBlocking { viewModel.callService().join() }

        assertEquals(MoviesViewModel.Status.ERROR, viewModel.getValue().value?.status)
    }
}
