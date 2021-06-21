package com.desafio.mytmdb.presentation.reducer

import com.desafio.mytmdb.domain.exception.UnsupportedReduceException
import com.desafio.mytmdb.common.mvi.MviReducer
import com.desafio.mytmdb.presentation.result.MoviesResult
import com.desafio.mytmdb.presentation.result.MoviesResult.LoadMoviesResult.*
import com.desafio.mytmdb.presentation.states.MoviesUiState
import com.desafio.mytmdb.presentation.states.MoviesUiState.*
import javax.inject.Inject

class MovieReducer @Inject constructor() : MviReducer<MoviesUiState, MoviesResult> {

    override fun MoviesUiState.reduce(result: MoviesResult): MoviesUiState {
        return when(val previousState: MoviesUiState = this){
            is DefaultUiState -> previousState reduceWith result
            is LoadingUiState -> previousState reduceWith result
            is DisplayingMoviesUiState -> previousState reduceWith result
            is ErrorUiState -> previousState reduceWith result
        }
    }

    private infix fun DefaultUiState.reduceWith(result: MoviesResult): MoviesUiState {
        return when(result) {
            is InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun LoadingUiState.reduceWith(result: MoviesResult): MoviesUiState =
        when (result) {
            is Success -> DisplayingMoviesUiState(result.movies)
            is Error -> ErrorUiState(result.error)
            else -> throw UnsupportedReduceException(this, result)
        }

    private infix fun ErrorUiState.reduceWith(result: MoviesResult): MoviesUiState =
        when (result) {
            is InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }

    private infix fun DisplayingMoviesUiState.reduceWith(result: MoviesResult): MoviesUiState =
        throw UnsupportedReduceException(this, result)


}