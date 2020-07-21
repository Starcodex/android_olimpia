package com.starcodex.data.auth.source.remote

import com.starcodex.data.auth.source.entity.ApiAccess
import com.starcodex.data.auth.source.entity.AuthUserBodyRequest
import io.reactivex.Observable
import retrofit2.http.*

interface AuthUserApiClient {

    @Headers("Content-Type: application/json",
        "X-Requested-With: XMLHttpRequest")
    @POST("api/auth/login")
    fun getApiToken(
        @Body body: AuthUserBodyRequest
    ): Observable<ApiAccess>
}