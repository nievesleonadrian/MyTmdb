package com.desafio.mytmdb.presentation.mapper

import com.desafio.mytmdb.data.remote.POSTER_BASE_URL
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.presentation.model.UiMovie
import javax.inject.Inject



class UiMovieMapper @Inject constructor() {

    fun List<DomainMovie>.fromDomainToUi(): List<UiMovie> {
        return map { domainMovie -> domainMovie.fromDomainToUi() }
    }

    private fun DomainMovie.fromDomainToUi() = UiMovie(
        id = id,
        overview = overview,
        poster_path = "$POSTER_BASE_URL$poster_path",
        title = title,
        vote_average = vote_average
    )
}