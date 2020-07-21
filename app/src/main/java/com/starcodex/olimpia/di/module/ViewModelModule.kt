package com.starcodex.olimpia.di.module

import androidx.lifecycle.ViewModelProvider
import com.starcodex.commons.app.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}