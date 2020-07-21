package com.starcodex.data.stepform.source.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.starcodex.domain.stepform.model.UserInfoResponse

class UserInfoApiClientResponse (
    @SerializedName("success")
    @Expose
    var success: String? = null
)
fun UserInfoApiClientResponse.mapToDomain() = UserInfoResponse(success)
