package com.desafio.mytmdb.ui

import android.view.View
import androidx.navigation.findNavController
import com.desafio.mytmdb.presentation.model.UiMovie
import javax.inject.Inject

class MoviesNavigator @Inject constructor() {

    fun goToMovieDetails(view: View, uiMovie: UiMovie){
        val direction = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(uiMovie)
        view.findNavController().navigate(direction)
    }

}