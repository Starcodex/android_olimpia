package com.starcodex.data.auth.source.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.starcodex.domain.auth.model.ApiDataAccess

class ApiAccess(
    @SerializedName("access_token")
    @Expose
    var access_token: String? = null,

    @SerializedName("token_type")
    @Expose
    var token_type: String? = null,

    @SerializedName("expires_at")
    @Expose
    var expires_at: String? = null
)

fun ApiAccess.mapToDomain() = ApiDataAccess(access_token, token_type, expires_at)