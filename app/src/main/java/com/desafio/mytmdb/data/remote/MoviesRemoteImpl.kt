package com.desafio.mytmdb.data.remote

import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.data.source.MoviesRemote
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MoviesRemoteImpl @Inject constructor(private val restApi: MoviesRestApi) : MoviesRemote {
    override fun getMovies(apiKey: String): Single<RemoteMoviesResponse> =
        restApi.popularMovies(apiKey)
}