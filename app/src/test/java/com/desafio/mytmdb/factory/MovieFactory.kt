package com.desafio.mytmdb.factory

import com.desafio.mytmdb.data.model.RemoteMovie
import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.domain.model.DomainMovie

object MovieFactory {

    fun makeDomainMovieList(count: Int) =
        (0..count).map { makeDomainMovie() }

    fun makeDomainMovie() = DomainMovie(
        id = RandomFactory.generateInt(),
        overview = RandomFactory.generateString(),
        poster_path = RandomFactory.generateString(),
        title = RandomFactory.generateString(),
        vote_average = RandomFactory.generateDouble()
    )

    fun makeRemoteMovie() = RemoteMovie(
        id = RandomFactory.generateInt(),
        overview = RandomFactory.generateString(),
        poster_path = RandomFactory.generateString(),
        title = RandomFactory.generateString(),
        vote_average = RandomFactory.generateDouble()
    )

    fun makeRemoteMovieList(count: Int) =
        (0..count).map { makeRemoteMovie() }

    fun makeRemoteMoviesResponse() = RemoteMoviesResponse(
        results = makeRemoteMovieList(9)
    )
}