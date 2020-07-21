package com.starcodex.domain.stepform.usecase

import android.util.Log
import com.starcodex.commons.app.ExecutionThreads
import com.starcodex.domain.auth.model.UserInfo
import com.starcodex.domain.stepform.model.UserInfoResponse
import com.starcodex.domain.stepform.repository.SaveUserInfoRepository
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SaveUserInfoUseCaseImpl @Inject constructor(
    private val saveUserInfoRepository: SaveUserInfoRepository,
    var executionThreads: ExecutionThreads
) : SaveUserInfoUseCase {

    override fun saveUserInfo(userInfo: UserInfo): Maybe<UserInfoResponse> {
        return saveUserInfoRepository.saveUserInfo(userInfo)
            .observeOn(executionThreads.ui())
    }

}