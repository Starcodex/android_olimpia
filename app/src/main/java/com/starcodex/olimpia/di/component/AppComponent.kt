package com.starcodex.olimpia.di.component

import com.starcodex.olimpia.OApplication
import com.starcodex.olimpia.di.module.ActivityBuildersModule
import com.starcodex.olimpia.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class])
interface AppComponent : AndroidInjector<OApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: OApplication): AppComponent
    }
}