package com.desafio.mytmdb.presentation.processor

import com.desafio.mytmdb.domain.GetMoviesUseCase
import com.desafio.mytmdb.presentation.action.MoviesAction
import com.desafio.mytmdb.presentation.action.MoviesAction.*
import com.desafio.mytmdb.presentation.mapper.UiMovieMapper
import com.desafio.mytmdb.common.mvi.ExecutionThread
import com.desafio.mytmdb.presentation.result.MoviesResult
import com.desafio.mytmdb.presentation.result.MoviesResult.*
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MoviesProcessor @Inject constructor(
    private val useCase: GetMoviesUseCase,
    private val mapper: UiMovieMapper,
    private val threadProvider: ExecutionThread
) {

    val actionProcessor: ObservableTransformer<MoviesAction, MoviesResult> =
        ObservableTransformer { actionObservable ->
            actionObservable.publish { actions ->
                actions.ofType(LoadMoviesAction::class.java)
                    .compose(loadMoviesProcessor)
            }
        }

    private val loadMoviesProcessor =
        ObservableTransformer<LoadMoviesAction, LoadMoviesResult> { actions ->
            actions.flatMap {
                useCase.getMovies()
                    .toObservable()
                    .map { with(mapper) {it.fromDomainToUi()} }
                    .map (LoadMoviesResult::Success)
                    .cast(LoadMoviesResult::class.java)
                    .onErrorReturn(LoadMoviesResult::Error)
                    .startWith(LoadMoviesResult.InProgress)
                    .subscribeOn(threadProvider.schedulerForSubscribing())
                    .observeOn(threadProvider.schedulerForObserving())
            }
        }

}