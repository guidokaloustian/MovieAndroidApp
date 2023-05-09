package com.example.movieapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.database.MovieDataBaseImpl
import com.example.movieapp.database.MovieRoomDataBase
import com.example.movieapp.databinding.ActivityMovieBinding
import com.example.movieapp.mvvm.contract.MainContract
import com.example.movieapp.mvvm.model.MainModel
import com.example.movieapp.mvvm.viewmodel.MainViewModel
import com.example.movieapp.mvvm.viewmodel.factory.ViewModelFactory
import com.example.movieapp.service.MovieClient
import com.example.movieapp.service.MovieRequestGenerator
import com.example.movieapp.service.MovieServiceImpl

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private lateinit var viewModel: MainContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val dataBase: MovieRoomDataBase by lazy {
            Room
                .databaseBuilder(this, MovieRoomDataBase::class.java, "Movie-DataBase")
                .build()
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                arrayOf(
                    MainModel(
                        MovieServiceImpl(MovieRequestGenerator.createService(MovieClient::class.java)),
                        MovieDataBaseImpl(dataBase.movieDao()),
                    ),
                ),
            ),
        )[MainViewModel::class.java]

        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MainViewModel.MainData) {
        when (data.status) {
            MainViewModel.Status.SHOW_INFO -> {
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}
