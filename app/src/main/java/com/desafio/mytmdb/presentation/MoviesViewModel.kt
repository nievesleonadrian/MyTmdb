package com.desafio.mytmdb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desafio.mytmdb.presentation.action.MoviesAction
import com.desafio.mytmdb.presentation.action.MoviesAction.*
import com.desafio.mytmdb.common.mvi.MviPresentation
import com.desafio.mytmdb.presentation.processor.MoviesProcessor
import com.desafio.mytmdb.presentation.reducer.MovieReducer
import com.desafio.mytmdb.presentation.result.MoviesResult
import com.desafio.mytmdb.presentation.states.MoviesUiState
import com.desafio.mytmdb.presentation.states.MoviesUiState.*
import com.desafio.mytmdb.presentation.userintents.MoviesUserIntent
import com.desafio.mytmdb.presentation.userintents.MoviesUserIntent.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val processor: MoviesProcessor,
    private val reducer: MovieReducer
) : ViewModel(), MviPresentation<MoviesUserIntent, MoviesUiState> {

    private val intentsSubject: PublishSubject<MoviesUserIntent> = PublishSubject.create()
    private val statesLiveData: MutableLiveData<MoviesUiState> = MutableLiveData()
    private val statesObservable: Observable<MoviesUiState> = compose()
    private var disposable = CompositeDisposable()

    init {
        disposable += statesObservable.subscribe(statesLiveData::setValue) {}
    }

    private fun compose(): Observable<MoviesUiState> {
        return intentsSubject
            .map { intent -> intent.toAction() }
            .compose(processor.actionProcessor)
            .scan(DefaultUiState, reducer())
    }

    private fun reducer(): BiFunction<MoviesUiState, MoviesResult, MoviesUiState> =
        BiFunction { previousState: MoviesUiState, result: MoviesResult ->
            with(reducer) { previousState.reduce(result) }
        }

    private fun MoviesUserIntent.toAction(): MoviesAction =
        when (this) {
            is SeeMoviesInitialUserIntent -> LoadMoviesAction
            is RetrySeeMoviesUserIntent -> LoadMoviesAction
        }

    override fun processUserIntents(userIntents: Observable<MoviesUserIntent>) {
        userIntents.subscribe(intentsSubject)
    }

    override fun uiStates(): Observable<MoviesUiState> = statesObservable

    fun liveData(): LiveData<MoviesUiState> = statesLiveData

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}