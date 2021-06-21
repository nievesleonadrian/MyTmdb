package com.desafio.mytmdb.data.remote

import com.desafio.mytmdb.data.model.MovieDetails
import com.desafio.mytmdb.data.model.RemoteMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "6e77bed5ae3a32ad5d398707ccb16c72"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500"

interface MoviesRestApi {

    @GET("movie/popular")
    fun popularMovies(): Single<RemoteMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

}