package com.starcodex.olimpia.di.module

import com.starcodex.olimpia.ui.stepform.StepContainerActivity
import com.starcodex.olimpia.ui.stepform.di.StepFormActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [StepFormActivityModule::class])
    abstract fun bindMainActivity(): StepContainerActivity

}