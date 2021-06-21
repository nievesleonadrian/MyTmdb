package com.desafio.mytmdb.domain

import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MoviesRepository){

    fun getMovies(): Single<List<DomainMovie>> = repository.getMovies()

}