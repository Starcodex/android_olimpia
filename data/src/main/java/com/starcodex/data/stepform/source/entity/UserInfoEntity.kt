package com.starcodex.data.stepform.source.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "USER_INFO")
class UserInfoEntity (
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id") @PrimaryKey
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    var name: String? = null,

    @SerializedName("id_number")
    @Expose
    @ColumnInfo(name = "id_number")
    var id_number: String? = null,

    @SerializedName("address")
    @Expose
    @ColumnInfo(name = "address")
    var address: String? = null,

    @SerializedName("city")
    @Expose
    @ColumnInfo(name = "city")
    var city: String? = null,

    @SerializedName("country")
    @Expose
    @ColumnInfo(name = "country")
    var country: String? = null,

    @SerializedName("phone_number")
    @Expose
    @ColumnInfo(name = "phone_number")
    var phone_number: Int? = null,

    @SerializedName("picture")
    @Expose
    @ColumnInfo(name = "picture")
    var picture: String? = null,

    @SerializedName("latitude")
    @Expose
    @ColumnInfo(name = "latitude")
    var latitude: Long? = null,

    @SerializedName("longitude")
    @Expose
    @ColumnInfo(name = "longitude")
    var longitude: Long? = null,

    @SerializedName("wifi_status")
    @Expose
    @ColumnInfo(name = "wifi_status")
    var wifi_status: String? = null,

    @SerializedName("bluetoooth_status")
    @Expose
    @ColumnInfo(name = "bluetooth_status")
    var bluetooth_status: String? = null
)