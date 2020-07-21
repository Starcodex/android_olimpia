package com.starcodex.data.stepform.repository

import com.starcodex.data.stepform.source.remote.UserInfoApiClient
import com.starcodex.data.stepform.source.remote.UserInfoApiClientResponse
import com.starcodex.data.stepform.source.remote.mapToDomain
import com.starcodex.domain.auth.model.UserInfo
import com.starcodex.domain.stepform.model.UserInfoResponse
import com.starcodex.domain.stepform.repository.SaveUserInfoRepository
import io.reactivex.Maybe
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject


class SaveUserInfoRepositoryImpl @Inject constructor(val userInfoApiClient: UserInfoApiClient) : SaveUserInfoRepository {
    override fun saveUserInfo(userInfo: UserInfo): Maybe<UserInfoResponse> {

        val file = File(userInfo.picture!!)
        val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val picture = MultipartBody.Part.createFormData("picture", file.getName(), requestFile)
        val name = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.name!!)
        val idNumber = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.id_number!!)
        val address = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.address!!)
        val city = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.city!!)
        val country = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.country!!)
        val phone_number = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.phone_number!!)
        val latitude = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.latitude!!.toString())
        val longitude = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.longitude!!.toString())
        val wifi_status = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.wifi_status!!)
        val bluetooth_status = RequestBody.create(MediaType.parse("multipart/form-data"), userInfo.bluetooth_status!!)

        return userInfoApiClient.sendUserAppInfo(
            name,
            idNumber,
            address,
            city,
            country,
            phone_number,
            picture,
            latitude,
            longitude,
            wifi_status,
            bluetooth_status

        ).map { it.mapToDomain() }
    }
}