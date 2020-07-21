package com.starcodex.olimpia.ui.stepform.di

import com.starcodex.data.auth.source.remote.AuthUserApiClient
import com.starcodex.data.stepform.source.remote.UserInfoApiClient
import com.starcodex.olimpia.di.module.DataModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = arrayOf(DataModule::class))
class StepFormActivityDataModule {
    @Provides
    fun provideAuthUserApiClient(retrofit: Retrofit) = retrofit.create(AuthUserApiClient::class.java)

    @Provides
    fun provideUserInfoApiClient(@Named("Auth") retrofit: Retrofit) = retrofit.create(UserInfoApiClient::class.java)
}