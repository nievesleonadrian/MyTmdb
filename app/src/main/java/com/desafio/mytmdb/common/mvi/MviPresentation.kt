package com.desafio.mytmdb.common.mvi

import io.reactivex.Observable

interface MviPresentation<TUserIntent : MviUserIntent, TUiState : MviUiState> {

    fun processUserIntents(userIntents: Observable<TUserIntent>)

    fun uiStates(): Observable<TUiState>

}