package com.desafio.mytmdb.domain.repository

import com.desafio.mytmdb.domain.model.DomainMovie
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {
    fun getMovies(apiKey: String): Single<List<DomainMovie>>
}