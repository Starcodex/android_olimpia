package com.starcodex.domain.stepform.usecase

import com.starcodex.domain.auth.model.UserInfo
import com.starcodex.domain.stepform.model.UserInfoResponse
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface SaveUserInfoUseCase {
    fun saveUserInfo(userInfo: UserInfo): Maybe<UserInfoResponse>
}