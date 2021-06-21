package com.desafio.mytmdb.data.mapper

import com.desafio.mytmdb.data.model.RemoteMovie
import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.common.mvi.DefaultValues
import javax.inject.Inject

class DataMovieMapper @Inject constructor(){

    fun RemoteMoviesResponse.fromRemoteToDomain(): List<DomainMovie> {
        requireNotNull(results) {"The offer List is required"}
        return results.map { remoteMovie ->
            remoteMovie.fromRemoteToDomain()
        }
    }

    private fun RemoteMovie.fromRemoteToDomain() = DomainMovie(
        id = id ?: DefaultValues.zeroInt(),
        overview = overview.orEmpty(),
        poster_path = poster_path.orEmpty(),
        title = title.orEmpty(),
        vote_average = vote_average ?: DefaultValues.zeroDouble()
    )
}