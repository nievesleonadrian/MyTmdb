package com.desafio.mytmdb.data.remote

import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import com.desafio.mytmdb.data.source.MoviesRemote
import io.reactivex.Single
import javax.inject.Inject

class MoviesRemoteImpl @Inject constructor(private val restApi: MoviesRestApi) : MoviesRemote {
    override fun getMovies(): Single<RemoteMoviesResponse> =
        restApi.popularMovies()
}