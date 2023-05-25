package com.example.movieapp.clean.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<MenuData> = MutableLiveData()
    fun getValue(): LiveData<MenuData> = mutableLiveData

    fun buttonPressed() {
        mutableLiveData.value = MenuData(MainStatus.SHOW_MOVIES)
    }

    data class MenuData(
        val status: MainStatus,
    )

    enum class MainStatus {
        SHOW_MOVIES,
    }


}