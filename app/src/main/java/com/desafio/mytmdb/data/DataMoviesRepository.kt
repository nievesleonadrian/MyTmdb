package com.desafio.mytmdb.data

import com.desafio.mytmdb.data.mapper.DataMovieMapper
import com.desafio.mytmdb.data.source.MoviesRemote
import com.desafio.mytmdb.domain.model.DomainMovie
import com.desafio.mytmdb.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class DataMoviesRepository @Inject constructor(
    private val remote: MoviesRemote,
    private val mapper: DataMovieMapper
) : MoviesRepository {
    override fun getMovies(): Single<List<DomainMovie>> =
        remote.getMovies()
            .map { movieResponse ->
                with(mapper) { movieResponse.fromRemoteToDomain() }
            }
}