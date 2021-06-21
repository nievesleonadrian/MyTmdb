package com.desafio.mytmdb.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val budget: Int,
    @SerializedName(RemoteConstants.MOVIE_ID) val id: Int,
    @SerializedName(RemoteConstants.OVERVIEW) val overview: String,
    val popularity: Double,
    @SerializedName(RemoteConstants.POSTER_PATH) val posterPath: String,
    @SerializedName(RemoteConstants.RELEASE_DATE) val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    @SerializedName(RemoteConstants.TITLE) val title: String,
    val video: Boolean,
    @SerializedName(RemoteConstants.VOTE_AVERAGE) val rating: Double
)