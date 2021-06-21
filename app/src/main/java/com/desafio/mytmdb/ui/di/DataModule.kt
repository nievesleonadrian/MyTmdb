package com.desafio.mytmdb.ui.di

import com.desafio.mytmdb.data.DataMoviesRepository
import com.desafio.mytmdb.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataMoviesRepository(dataRepository : DataMoviesRepository) : MoviesRepository
}