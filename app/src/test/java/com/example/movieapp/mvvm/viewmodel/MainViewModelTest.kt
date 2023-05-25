package com.example.movieapp.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.clean.presentation.mvvm.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun `when the button is pressed, the status changes to SHOW_MOVIES`() {
        mainViewModel.buttonPressed()
        assertEquals(MainViewModel.MainStatus.SHOW_MOVIES, mainViewModel.getValue().value?.status)
    }

}