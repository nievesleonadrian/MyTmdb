package com.desafio.mytmdb.data.model

import com.google.gson.annotations.SerializedName

data class RemoteMoviesResponse(
    @SerializedName(RemoteConstants.RESULTS) val results: List<RemoteMovie>?
)
