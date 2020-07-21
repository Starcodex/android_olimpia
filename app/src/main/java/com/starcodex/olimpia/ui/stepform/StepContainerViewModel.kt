package com.starcodex.olimpia.ui.stepform

import androidx.lifecycle.ViewModel
import com.starcodex.domain.auth.repository.AuthRepository
import com.starcodex.domain.auth.usecase.GetApiAccessUsecase
import javax.inject.Inject

class StepContainerViewModel
@Inject constructor(private val getApiAccessUsecase: GetApiAccessUsecase) : ViewModel() {
    fun getApiAccess() = getApiAccessUsecase.getApiAccess()
}