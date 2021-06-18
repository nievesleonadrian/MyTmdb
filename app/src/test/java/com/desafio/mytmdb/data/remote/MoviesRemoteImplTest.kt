package com.desafio.mytmdb.data.remote

import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.factory.MovieFactory
import com.desafio.mytmdb.factory.RandomFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MoviesRemoteImplTest{

    private val restApi = mock<MoviesRestApi>()
    private val remote = MoviesRemoteImpl(restApi)
    private val apiKey = RandomFactory.generateString()

    @Test
    fun `given remoteMoviesResponse, when getMovies, then return data`() {
        val remoteMoviesResponse = MovieFactory.makeRemoteMoviesResponse()
        stubRestApiPopularMovies(Single.just(remoteMoviesResponse))

        val testObserver = remote.getMovies(apiKey).test()

        testObserver.assertValue(remoteMoviesResponse)
    }

    private fun stubRestApiPopularMovies(single: Single<RemoteMoviesResponse>) {
        whenever(remote.getMovies(apiKey)).thenReturn(single)
    }
}