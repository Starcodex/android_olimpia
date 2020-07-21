package com.starcodex.data.stepform.source.remote

import io.reactivex.Maybe
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface UserInfoApiClient {

    @Multipart
    @POST("api/auth/module/userappinfo/storeData")
    fun sendUserAppInfo(
        @Part("name") name : RequestBody,
        @Part("id_number") id_number : RequestBody,
        @Part("address") address: RequestBody,
        @Part("city") city: RequestBody,
        @Part("country") country: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
        @Part picture: MultipartBody.Part,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part("wifi_status") wifi_status: RequestBody,
        @Part("bluetooth_status") bluetooth_status: RequestBody
    ): Maybe<UserInfoApiClientResponse>
}