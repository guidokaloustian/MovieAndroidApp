package com.example.movieapp.clean.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemRecyclerBinding
import com.example.movieapp.clean.data.service.model.Movie

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false),
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecyclerBinding.bind(itemView)

        fun bind(movie: Movie) {
            binding.title.text = itemView.context.getString(R.string.card_title, movie.title)
            binding.voteAverage.text = itemView.context.getString(R.string.card_votes, movie.voteAverage)
            binding.date.text = itemView.context.getString(R.string.card_date, movie.releaseDate)

            Glide.with(itemView.context)
                .load(imageBaseUrl + movie.image)
                .into(binding.image)
        }
        companion object {
            private const val imageBaseUrl = "https://image.tmdb.org/t/p/w500"
        }
    }
}
