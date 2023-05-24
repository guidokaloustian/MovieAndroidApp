package com.example.movieapp.clean.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.clean.presentation.adapter.MovieAdapter
import com.example.movieapp.clean.domain.util.errorfragment.ErrorDialogFragment
import com.example.movieapp.clean.presentation.mvvm.viewmodel.MoviesViewModel
import com.example.movieapp.databinding.ActivityMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        moviesViewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MoviesViewModel.MainData) {
        when (data.status) {
            MoviesViewModel.Status.SHOW_INFO -> {
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)
            }
            MoviesViewModel.Status.ERROR -> {
                ErrorDialogFragment.newInstance(
                    getString(R.string.error_string_title),
                    getString(R.string.error_description_text),
                ).show(supportFragmentManager, getString(R.string.error_string))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.callService()
    }
}
