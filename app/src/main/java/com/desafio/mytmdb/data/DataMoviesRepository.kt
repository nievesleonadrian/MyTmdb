package com.desafio.mytmdb.data

import com.desafio.mytmdb.data.mapper.DataMovieMapper
import com.desafio.mytmdb.data.source.MoviesRemote
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.domain.repository.MoviesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DataMoviesRepository @Inject constructor(
    private val remote: MoviesRemote,
    private val mapper: DataMovieMapper
) : MoviesRepository {
    override fun getMovies(apiKey: String): Single<List<DomainMovie>> =
        remote.getMovies(apiKey)
            .map { movieResponse ->
                with(mapper) { movieResponse.fromRemoteToDomain() }
            }
}