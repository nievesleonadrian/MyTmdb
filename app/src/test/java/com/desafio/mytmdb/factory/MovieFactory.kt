package com.desafio.mytmdb.factory

import com.desafio.mytmdb.data.model.RemoteMovie
import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.presentation.model.UiMovie

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

    fun makeRemoteMoviesResponse(count: Int) = RemoteMoviesResponse(
        results = makeRemoteMovieList(count)
    )

    fun makeUiMovieList(count: Int) =
        (0..count).map { makeUiMovie() }

    private fun makeUiMovie() = UiMovie(
        id = RandomFactory.generateInt(),
        overview = RandomFactory.generateString(),
        poster_path = RandomFactory.generateString(),
        title = RandomFactory.generateString(),
        vote_average = RandomFactory.generateDouble()
    )
}