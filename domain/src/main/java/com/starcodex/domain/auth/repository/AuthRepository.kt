package com.starcodex.domain.auth.repository

import com.starcodex.domain.auth.model.ApiDataAccess
import io.reactivex.Observable

interface AuthRepository {
    fun refreshApiTokenIfNeeded()
    fun retrieveRemoteApiAccess() : Observable<ApiDataAccess>
    fun getLocalApiToken() : String
    fun getLocalApiDataAccess() : ApiDataAccess
    fun saveApiDataAccess(apiAccess: ApiDataAccess)
}