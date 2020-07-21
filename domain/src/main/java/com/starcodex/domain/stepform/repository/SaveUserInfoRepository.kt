package com.starcodex.domain.stepform.repository

import com.starcodex.domain.auth.model.UserInfo
import com.starcodex.domain.stepform.model.UserInfoResponse
import io.reactivex.Maybe
import io.reactivex.Single

interface SaveUserInfoRepository {
    fun saveUserInfo(userInfo: UserInfo): Maybe<UserInfoResponse>
}