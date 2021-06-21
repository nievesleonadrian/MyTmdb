package com.desafio.mytmdb.presentation.action

import com.desafio.mytmdb.common.mvi.MviAction

sealed class MoviesAction : MviAction {

    object LoadMoviesAction : MoviesAction()
}