package com.desafio.mytmdb.ui.di

import com.desafio.mytmdb.common.di.ActivityScope
import com.desafio.mytmdb.common.di.ApplicationComponent
import com.desafio.mytmdb.MainActivity
import com.desafio.mytmdb.ui.MoviesFragment
import dagger.Component

@ActivityScope
@Component(modules = [
    PresentationModule::class,
    DataModule::class,
    UiModule::class,
    RemoteModule::class,
], dependencies = [ApplicationComponent::class]
)
interface MoviesComponent {
   fun inject(fragment : MoviesFragment)
   fun inject(activity : MainActivity)
}