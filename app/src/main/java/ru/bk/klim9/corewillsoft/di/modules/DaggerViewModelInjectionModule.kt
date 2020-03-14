package ru.bk.klim9.corewillsoft.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Configures bindings to [DaggerViewModelFactory], injectable into a [ViewModelProvider.Factory].
 */
@Suppress("unused")
@Module
abstract class DaggerViewModelInjectionModule {
    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}