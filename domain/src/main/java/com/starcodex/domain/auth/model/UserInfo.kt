package com.starcodex.domain.auth.model

class UserInfo(
    var name: String? = null,
    var id_number: String? = null,
    var address: String? = null,
    var city: String? = null,
    var country: String? = null,
    var phone_number: String? = null,
    var picture: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var wifi_status: String? = null,
    var bluetooth_status: String? = null
)