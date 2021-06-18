package com.desafio.mytmdb.domain

import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.domain.repository.MoviesRepository
import com.desafio.mytmdb.factory.MovieFactory
import com.desafio.mytmdb.factory.RandomFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetMoviesUseCaseTest {

    private val repository = mock<MoviesRepository>()
    private val useCase = GetMoviesUseCase(repository)

    private val apiKey = RandomFactory.generateString()

    @Test
    fun `given DomainMovies, when getMovies, then return data`() {
        val domainMovies = MovieFactory.makeDomainMovieList(5)
        stubRepositoryGetMovies(Single.just(domainMovies))
        val testObserver = useCase.getMovies(apiKey).test()

        testObserver.assertValue(domainMovies)
    }

    private fun stubRepositoryGetMovies(single: Single<List<DomainMovie>>) {
        whenever(repository.getMovies(apiKey)).thenReturn(single)
    }

}