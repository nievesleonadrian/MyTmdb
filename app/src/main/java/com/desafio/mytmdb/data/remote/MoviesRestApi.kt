package com.desafio.mytmdb.data.remote

import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRestApi {
    @GET("movie/popular")
    fun popularMovies(@Query("api_key") api_key: String, ): Single<RemoteMoviesResponse>
}