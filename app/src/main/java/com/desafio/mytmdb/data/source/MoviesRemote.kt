package com.desafio.mytmdb.data.source

import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import io.reactivex.Single

interface MoviesRemote {

    fun getMovies(): Single<RemoteMoviesResponse>
}