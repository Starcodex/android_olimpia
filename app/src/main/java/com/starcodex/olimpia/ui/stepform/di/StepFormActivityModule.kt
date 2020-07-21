package com.starcodex.olimpia.ui.stepform.di

import androidx.lifecycle.ViewModel
import com.starcodex.data.auth.repository.AuthUserRepositoryImpl
import com.starcodex.data.stepform.repository.SaveUserInfoRepositoryImpl
import com.starcodex.domain.auth.repository.AuthRepository
import com.starcodex.domain.auth.usecase.GetApiAccessUsecase
import com.starcodex.domain.auth.usecase.GetApiAccessUsecaseImpl
import com.starcodex.domain.stepform.repository.SaveUserInfoRepository
import com.starcodex.domain.stepform.usecase.SaveUserInfoUseCase
import com.starcodex.domain.stepform.usecase.SaveUserInfoUseCaseImpl
import com.starcodex.olimpia.di.annotations.ViewModelKey
import com.starcodex.olimpia.ui.stepform.ActivityPagerControl
import com.starcodex.olimpia.ui.stepform.StepContainerActivity
import com.starcodex.olimpia.ui.stepform.StepContainerViewModel
import com.starcodex.olimpia.ui.stepform.fragment.*
import com.starcodex.olimpia.ui.stepform.fragment.stepdata.StepDataFragment
import com.starcodex.olimpia.ui.stepform.fragment.stepsave.StepSaveFragment
import com.starcodex.olimpia.ui.stepform.fragment.stepsave.StepSaveViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = arrayOf(StepFormActivityDataModule::class))
abstract class StepFormActivityModule {

    // Bind Fragments
    @ContributesAndroidInjector
    abstract fun contributeStepDataFragment(): StepDataFragment

    @ContributesAndroidInjector
    abstract fun contributeStepPictureFragment(): StepPictureFragment

    @ContributesAndroidInjector
    abstract fun contributeStepGeoFragment(): StepGeoFragment

    @ContributesAndroidInjector
    abstract fun contributeStepStatusFragment(): StepStatusFragment

    @ContributesAndroidInjector
    abstract fun contributeStepSaveFragment(): StepSaveFragment

    // Bind Activity Interactor
    @Binds
    abstract fun bindPagerControlActivityInteractor(activity: StepContainerActivity) : ActivityPagerControl

    // Bind Activity ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(StepContainerViewModel::class)
    abstract fun bindStepContainerViewModel(stepContainerViewModel: StepContainerViewModel): ViewModel

    // Bind Activity ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(StepSaveViewModel::class)
    abstract fun bindStepSaveViewModel(stepSaveViewModel: StepSaveViewModel): ViewModel

   //Bind UseCase
    @Binds
    abstract fun bindGetApiKeyUseCase(useCaseImpl: GetApiAccessUsecaseImpl): GetApiAccessUsecase

    // Bind Repository
    @Binds
    abstract fun bindAuthRepository(repositoryImpl: AuthUserRepositoryImpl): AuthRepository

    //Bind UseCase
    @Binds
    abstract fun bindSaveUserInfoUseCase(useCaseImpl: SaveUserInfoUseCaseImpl): SaveUserInfoUseCase

    // Bind Repository
    @Binds
    abstract fun bindSaveUserInfoRepository(repositoryImpl: SaveUserInfoRepositoryImpl): SaveUserInfoRepository

}