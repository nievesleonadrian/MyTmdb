package com.desafio.mytmdb.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.desafio.mytmdb.common.AppExecutionThread
import com.desafio.mytmdb.common.ViewModelFactory
import com.desafio.mytmdb.common.ViewModelKey
import com.desafio.mytmdb.common.mvi.ExecutionThread
import com.desafio.mytmdb.presentation.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    abstract fun bindExecutionThread(executionThread: AppExecutionThread): ExecutionThread


}