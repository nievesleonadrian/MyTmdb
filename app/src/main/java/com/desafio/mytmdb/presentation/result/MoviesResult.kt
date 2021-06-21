package com.desafio.mytmdb.presentation.result

import com.desafio.mytmdb.presentation.model.UiMovie
import com.desafio.mytmdb.common.mvi.MviResult

sealed class MoviesResult : MviResult {

    sealed class LoadMoviesResult : MoviesResult() {
        object InProgress : LoadMoviesResult()
        data class Success(val movies: List<UiMovie>) : LoadMoviesResult()
        data class Error(val error: Throwable) : LoadMoviesResult()
    }
}
