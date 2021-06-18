package com.desafio.mytmdb.data.model

import com.google.gson.annotations.SerializedName

data class RemoteMovie(
    @SerializedName(RemoteConstants.MOVIE_ID) val id: Int?,
    @SerializedName(RemoteConstants.OVERVIEW) val overview: String?,
    @SerializedName(RemoteConstants.POSTER_PATH) val poster_path: String?,
    @SerializedName(RemoteConstants.TITLE) val title: String?,
    @SerializedName(RemoteConstants.VOTE_AVERAGE) val vote_average: Double?
)