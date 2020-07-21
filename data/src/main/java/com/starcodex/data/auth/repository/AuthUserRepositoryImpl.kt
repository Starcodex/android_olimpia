package com.starcodex.data.auth.repository

import com.starcodex.commons.BuildConfig.PASS
import com.starcodex.commons.BuildConfig.USER
import com.starcodex.data.auth.source.entity.ApiAccess
import com.starcodex.data.auth.source.entity.AuthPrefs
import com.starcodex.data.auth.source.entity.AuthUserBodyRequest
import com.starcodex.data.auth.source.entity.mapToDomain
import com.starcodex.data.auth.source.remote.AuthUserApiClient
import com.starcodex.domain.auth.model.ApiDataAccess
import com.starcodex.domain.auth.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class AuthUserRepositoryImpl @Inject constructor(
    private val authUserApiClient: AuthUserApiClient,
    private val authPrefs : AuthPrefs
) : AuthRepository{

    override fun refreshApiTokenIfNeeded() {

    }

    override fun retrieveRemoteApiAccess(): Observable<ApiDataAccess> {
        return authUserApiClient
            .getApiToken(AuthUserBodyRequest(email = USER, password = PASS, password_confirmation = PASS))
            .map { it.mapToDomain() }
    }

    override fun getLocalApiToken() : String {
        return authPrefs.getApiToken()!!
    }

    override fun getLocalApiDataAccess() : ApiDataAccess {
        return authPrefs.getApiTokenData().mapToDomain()
    }

    override fun saveApiDataAccess(apiAccess: ApiDataAccess) {
        authPrefs.saveApiTokenData(apiAccess)
    }

}