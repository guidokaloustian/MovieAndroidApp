package com.example.movieapp.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.mvvm.contract.MainContract
import com.example.movieapp.service.model.Movie
import com.example.movieapp.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var model: MainContract.Model

    @MockK
    private lateinit var movieList: List<Movie>

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainViewModel(model)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when the service call is successful, the data status should be 'SHOW_INFO'`() {
        coEvery { model.getMovies() } returns CoroutineResult.Success(movieList)

        runBlocking { viewModel.callService().join() }

        assertEquals(movieList, viewModel.getValue().value?.movies)
        assertEquals(MainViewModel.Status.SHOW_INFO, viewModel.getValue().value?.status)
    }

    @Test
    fun `when the service call fails, the data status should be 'ERROR'`() {
        coEvery { model.getMovies() } returns CoroutineResult.Failure(Exception())

        runBlocking { viewModel.callService().join() }

        assertEquals(MainViewModel.Status.ERROR, viewModel.getValue().value?.status)
    }
}
