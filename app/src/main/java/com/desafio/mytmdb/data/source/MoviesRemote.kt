package com.desafio.mytmdb.data.source

import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import io.reactivex.rxjava3.core.Single

interface MoviesRemote {

    fun getMovies(apiKey: String): Single<RemoteMoviesResponse>
}