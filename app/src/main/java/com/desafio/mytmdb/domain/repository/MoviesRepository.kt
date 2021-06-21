package com.desafio.mytmdb.domain.repository

import com.desafio.mytmdb.domain.model.DomainMovie
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(): Single<List<DomainMovie>>
}