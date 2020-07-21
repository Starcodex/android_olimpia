package com.starcodex.olimpia.di.module

import android.content.Context
import com.starcodex.commons.app.ExecutionThreads
import com.starcodex.olimpia.OApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module(includes = arrayOf(DataModule::class, ViewModelModule::class))
class AppModule {

    @Provides
    fun provideAppContext(app: OApplication) : Context = app.applicationContext

    @Provides
    fun provideExecutionThreads(): ExecutionThreads {
        return object : ExecutionThreads {
            override fun ui(): Scheduler = AndroidSchedulers.mainThread()
            override fun io(): Scheduler = Schedulers.io()
        }
    }

}