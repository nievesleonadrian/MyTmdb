package com.desafio.mytmdb.ui.di

import com.desafio.mytmdb.ui.provider.UiMoviesComponentProvider
import com.desafio.mytmdb.ui.provider.UiMoviesComponentProviderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UiModule {

    @Binds
    abstract fun UiMoviesComponentProviderImpl.bindProvider(): UiMoviesComponentProvider

}