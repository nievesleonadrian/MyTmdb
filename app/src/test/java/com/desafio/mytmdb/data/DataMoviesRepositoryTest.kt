package com.desafio.mytmdb.data

import com.desafio.mytmdb.data.mapper.DataMovieMapper
import com.desafio.mytmdb.data.source.MoviesRemote
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.factory.MovieFactory.makeDomainMovieList
import com.desafio.mytmdb.factory.RandomFactory
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DataMoviesRepositoryTest {

    private val remote = mock<MoviesRemote>()
    private val mapper = mockk<DataMovieMapper>()
    private val repository = DataMoviesRepository(remote, mapper)
    private val apiKey = RandomFactory.generateString()

    @Test
    fun `given DomainMovies list, when getMovies, then return data`() {
        val domainMovies = makeDomainMovieList(9)
        stubRemoteGetMovies(Single.just(domainMovies))

        val testObserver = repository.getMovies(apiKey).test()

        testObserver.assertValue(domainMovies)
    }

    private fun stubRemoteGetMovies(single: Single<List<DomainMovie>>) {
        whenever(repository.getMovies(apiKey)).thenReturn(single)
    }
}