package com.desafio.mytmdb.presentation.mapper

import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.factory.MovieFactory
import com.desafio.mytmdb.presentation.model.UiMovie
import org.junit.Assert.*
import org.junit.Test

class UiMovieMapperTest {

    private val mapper = UiMovieMapper()

    @Test
    fun `given DomainMovies list, when fromDomainToUi, then return UiMovie list`() {
        val domainMovies = MovieFactory.makeDomainMovieList(9)

        val uiMovies  = with(mapper) { domainMovies.fromDomainToUi() }

        assertMovieEquals(domainMovies, uiMovies)
    }

    private fun assertMovieEquals(listDomainModel: List<DomainMovie>, listUiModel: List<UiMovie>) {
        listDomainModel.zip(listUiModel).map {
            assertEquals("id", it.first.id, it.second.id)
            assertEquals("overview", it.first.overview, it.second.overview)
            assertEquals("poster_path", it.first.poster_path, it.second.poster_path)
            assertEquals("title", it.first.title, it.second.title)
            assertEquals("vote_average", it.first.vote_average.toString(), it.second.vote_average.toString())
        }
    }
}