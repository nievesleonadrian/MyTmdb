package com.desafio.mytmdb.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiMovie(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double
): Parcelable
