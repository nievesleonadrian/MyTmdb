package com.desafio.mytmdb.domain

import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.domain.repository.MoviesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MoviesRepository){

    fun getMovies(apiKey: String): Single<List<DomainMovie>> = repository.getMovies(apiKey)

}