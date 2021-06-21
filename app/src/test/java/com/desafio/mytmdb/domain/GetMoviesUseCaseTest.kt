package com.desafio.mytmdb.domain

import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.domain.repository.MoviesRepository
import com.desafio.mytmdb.factory.MovieFactory
import com.desafio.mytmdb.factory.RandomFactory
import io.reactivex.Single
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetMoviesUseCaseTest {

    private val repository = mock<MoviesRepository>()
    private val useCase = GetMoviesUseCase(repository)

    @Test
    fun `given DomainMovies, when getMovies, then return data`() {
        val domainMovies = MovieFactory.makeDomainMovieList(5)
        stubRepositoryGetMovies(Single.just(domainMovies))
        val testObserver = useCase.getMovies().test()

        testObserver.assertValue(domainMovies)
    }

    private fun stubRepositoryGetMovies(single: Single<List<DomainMovie>>) {
        whenever(repository.getMovies()).thenReturn(single)
    }

}