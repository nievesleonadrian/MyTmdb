package com.desafio.mytmdb.presentation.states

import com.desafio.mytmdb.presentation.model.UiMovie
import com.desafio.mytmdb.common.mvi.MviUiState

sealed class MoviesUiState : MviUiState {
    object DefaultUiState : MoviesUiState()
    object LoadingUiState : MoviesUiState()
    data class DisplayingMoviesUiState(val movies: List<UiMovie>) : MoviesUiState()
    data class ErrorUiState(val error: Throwable) : MoviesUiState()
}