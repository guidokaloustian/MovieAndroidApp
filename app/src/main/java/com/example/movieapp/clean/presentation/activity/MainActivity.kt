package com.example.movieapp.clean.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.clean.presentation.mvvm.viewmodel.MainViewModel
import com.example.movieapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStartedButton.setOnClickListener {
            mainViewModel.buttonPressed()
        }
        mainViewModel.getValue().observe(this) {
            updateUI(it)
        }
    }

    private fun updateUI(data: MainViewModel.MenuData) {
        when (data.status) {
            MainViewModel.MainStatus.SHOW_MOVIES -> {
                val intent = Intent(this, MovieActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
