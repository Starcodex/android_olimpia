package com.starcodex.olimpia.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

import androidx.room.Room
import com.starcodex.commons.BuildConfig.DB_NAME
import com.starcodex.data.AppDatabase
import com.starcodex.data.auth.source.entity.AuthPrefs
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(AppModule::class, ApiModule::class))
class DataModule {

    @Provides
    internal fun provideAuthPrefs(appContext: Context) = AuthPrefs(appContext)

    @Provides
    fun providesAppDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

}