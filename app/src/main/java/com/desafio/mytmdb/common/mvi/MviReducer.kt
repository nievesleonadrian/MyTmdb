package com.desafio.mytmdb.common.mvi

public interface MviReducer<TUiState : MviUiState, TResult : MviResult> {
    public abstract infix fun TUiState.reduce(result: TResult): TUiState
}