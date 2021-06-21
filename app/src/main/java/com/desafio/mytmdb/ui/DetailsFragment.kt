package com.desafio.mytmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.desafio.mytmdb.databinding.FragmentMovieDetailBinding
import com.desafio.mytmdb.presentation.model.UiMovie
import com.squareup.picasso.Picasso

class DetailsFragment: Fragment() {

    private var binding: FragmentMovieDetailBinding? = null

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDetails(args.uiMovie)
    }

    private fun setupDetails(uiMovie: UiMovie) {
        binding?.apply {
            movieOverview?.text = uiMovie.overview
            movieTitle?.text = uiMovie.title
            movieRating?.text = uiMovie.vote_average.toString()
            Glide.with(requireContext())
                .load(uiMovie.poster_path)
                .into(ivMoviePoster)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }
}