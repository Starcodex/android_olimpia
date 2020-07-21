package com.starcodex.olimpia.ui.stepform

import com.google.android.libraries.maps.model.LatLng
import com.starcodex.domain.auth.model.UserInfo

interface ActivityPagerControl {
    fun nextPage()
    fun previouspage()
    fun updatePersonalData(
        name: String,
        idNumber: String,
        address: String,
        city: String,
        country: String,
        phoneNumber: String
    )

    fun updatePicturePath(currentPhotoPath: String)
    fun updateCurrentLocation(location: LatLng)
    fun updateWBStatus(wifi: String, bluetooth: String)
    fun getUserInfo(): UserInfo
}