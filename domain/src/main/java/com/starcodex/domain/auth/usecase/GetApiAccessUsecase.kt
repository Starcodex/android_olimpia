package com.starcodex.domain.auth.usecase

interface GetApiAccessUsecase {
    fun getApiAccess()
    fun getLocalApiToken()
    fun getRemoteApiAccess()
}