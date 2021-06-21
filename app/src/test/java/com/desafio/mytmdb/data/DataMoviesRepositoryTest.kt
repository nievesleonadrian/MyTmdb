package com.desafio.mytmdb.data

import com.desafio.mytmdb.data.mapper.DataMovieMapper
import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.data.source.MoviesRemote
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.factory.MovieFactory.makeDomainMovieList
import com.desafio.mytmdb.factory.MovieFactory.makeRemoteMoviesResponse
import com.desafio.mytmdb.factory.RandomFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class DataMoviesRepositoryTest {

    private val remote = mock<MoviesRemote>()
    private val mapper = mock<DataMovieMapper>()
    private val repository = DataMoviesRepository(remote, mapper)
    private val stubRemoteMoviesResponse: RemoteMoviesResponse = makeRemoteMoviesResponse(9)

    @Test
    fun `given MovieRepository list, when getMovies, then return data`() {

        stubRemoteMovieSourceGetMovies(Single.just(stubRemoteMoviesResponse))

        val testObserver = repository.getMovies().test()

        testObserver.assertComplete()
    }

    private fun stubRemoteMovieSourceGetMovies(single : Single<RemoteMoviesResponse>)  {
        whenever(remote.getMovies()).thenReturn(single)
    }
}