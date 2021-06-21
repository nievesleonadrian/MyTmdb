package com.desafio.mytmdb.data.mapper

import com.desafio.mytmdb.data.model.RemoteMovie
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.factory.MovieFactory
import org.junit.Assert.*
import org.junit.Test

class DataMovieMapperTest {

    private val mapper = DataMovieMapper()

    @Test
    fun `given RemoteMoviesResponse, when fromRemoteToDomain, then return DomainMovie List`() {
        val remoteMoviesResponse = MovieFactory.makeRemoteMoviesResponse(5)

        val domainMovies  = with(mapper) { remoteMoviesResponse.fromRemoteToDomain() }

        assertMovieEquals(remoteMoviesResponse.results!!, domainMovies)
    }

    private fun assertMovieEquals(listRemoteModel: List<RemoteMovie>, listDomainModel: List<DomainMovie>) {
        listRemoteModel.zip(listDomainModel).map {
            assertEquals("id", it.first.id, it.second.id)
            assertEquals("overview", it.first.overview, it.second.overview)
            assertEquals("poster_path", it.first.poster_path, it.second.poster_path)
            assertEquals("title", it.first.title, it.second.title)
            assertEquals("vote_average", it.first.vote_average, it.second.vote_average)
        }
    }
}