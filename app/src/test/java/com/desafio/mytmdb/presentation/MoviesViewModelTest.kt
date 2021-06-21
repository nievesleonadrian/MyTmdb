package com.desafio.mytmdb.presentation

import com.desafio.mytmdb.domain.GetMoviesUseCase
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.factory.FakeExecutionThread
import com.desafio.mytmdb.factory.MovieFactory
import com.desafio.mytmdb.factory.RandomFactory
import com.desafio.mytmdb.presentation.mapper.UiMovieMapper
import com.desafio.mytmdb.presentation.model.UiMovie
import com.desafio.mytmdb.presentation.processor.MoviesProcessor
import com.desafio.mytmdb.presentation.reducer.MovieReducer
import com.desafio.mytmdb.presentation.states.MoviesUiState
import com.desafio.mytmdb.presentation.states.MoviesUiState.*
import com.desafio.mytmdb.presentation.userintents.MoviesUserIntent.SeeMoviesInitialUserIntent
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.whenever

class MoviesViewModelTest {

    private val mapper = mock<UiMovieMapper>()
    private val useCase = mock<GetMoviesUseCase>()
    private val processor = MoviesProcessor(
        mapper = mapper,
        useCase = useCase,
        threadProvider = FakeExecutionThread()
    )

    private val reducer = MovieReducer()

    private val viewModel = MoviesViewModel(
        processor = processor,
        reducer = reducer
    )

    private lateinit var testObserver: TestObserver<MoviesUiState>

    @Before
    fun setUp() {
        testObserver = viewModel.uiStates().test()
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    @Test
    fun `viewModel liveData not null`() {
        assertNotNull(viewModel.liveData())
    }

    @Test
    fun `state default`() {
        testObserver.assertValueAt(0) { it is DefaultUiState}
    }

    @Test
    fun `given movies from use case, when call processIntent, then get DisplayingMoviesUiState`() {
        val domainMovies = MovieFactory.makeDomainMovieList(3)
        stubUseCase(Single.just(domainMovies))
        val uiMovies = MovieFactory.makeUiMovieList(3)
        stubMapper(domainMovies, uiMovies)

        viewModel.processUserIntents(Observable.just(SeeMoviesInitialUserIntent))

        testObserver.assertValueAt(0) { state -> state is DefaultUiState }
        testObserver.assertValueAt(1) { state -> state is LoadingUiState }
        testObserver.assertValueAt(2) { state -> state is DisplayingMoviesUiState }
    }

    @Test
    fun `given an error from use case, when call processIntent, then get ErrorUiState`() {
        stubUseCase(Single.error(Throwable()))

        viewModel.processUserIntents(Observable.just(SeeMoviesInitialUserIntent))

        testObserver.assertValueAt(0) { state -> state is DefaultUiState }
        testObserver.assertValueAt(1) { state -> state is LoadingUiState }
        testObserver.assertValueAt(2) { state -> state is ErrorUiState }
    }

    private fun stubMapper(domainMovies: List<DomainMovie>, uiMovies: List<UiMovie>) {
        with(mapper) {
            whenever(domainMovies.fromDomainToUi()).thenReturn(uiMovies)
        }
    }

    private fun stubUseCase(single: Single<List<DomainMovie>>) {
        whenever(useCase.getMovies()).thenReturn(single)
    }

}