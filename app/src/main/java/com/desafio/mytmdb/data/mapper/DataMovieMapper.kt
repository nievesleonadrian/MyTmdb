package com.desafio.mytmdb.data.mapper

import com.desafio.mytmdb.data.model.RemoteMovie
import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.domain.model.DomainMovie

class DataMovieMapper {

    fun RemoteMoviesResponse.fromRemoteToDomain() = results.map { remoteMovie ->
        remoteMovie.fromRemoteToDomain()
    }

    fun RemoteMovie.fromRemoteToDomain() = DomainMovie(
        id = id ?: 0,
        overview = overview.orEmpty(),
        poster_path = poster_path.orEmpty(),
        title = title.orEmpty(),
        vote_average = vote_average ?: 0.0
    )
}