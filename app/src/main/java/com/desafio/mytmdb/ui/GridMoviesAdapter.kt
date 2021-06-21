package com.desafio.mytmdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafio.mytmdb.databinding.UiMovieItemBinding
import com.desafio.mytmdb.presentation.model.UiMovie
import com.squareup.picasso.Picasso
import javax.inject.Inject

class GridMoviesAdapter @Inject constructor(private val navigator: MoviesNavigator) : RecyclerView.Adapter<GridMoviesAdapter.ViewHolder>() {

    private var movies: List<UiMovie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UiMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiMovie = movies[position]
        holder.renderUi(uiMovie)
        holder.itemView.setOnClickListener {
           navigator.goToMovieDetails(it, uiMovie)
        }
    }


    fun setData(movies: List<UiMovie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: UiMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun renderUi(uiMovie: UiMovie) {
            with(binding) {
                tvTitle.text = uiMovie.title
                Glide.with(ivPoster.rootView.context)
                    .load(uiMovie.poster_path)
                    .into(ivPoster)
            }
        }

    }
}