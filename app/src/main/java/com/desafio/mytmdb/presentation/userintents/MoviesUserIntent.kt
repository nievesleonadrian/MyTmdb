package com.desafio.mytmdb.presentation.userintents

import com.desafio.mytmdb.common.mvi.MviUserIntent

sealed class MoviesUserIntent : MviUserIntent {
    object SeeMoviesInitialUserIntent : MoviesUserIntent()
    object RetrySeeMoviesUserIntent : MoviesUserIntent()
}