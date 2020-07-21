package com.starcodex.olimpia.util.arch

import androidx.annotation.StringRes
import com.starcodex.olimpia.util.arch.Status.*

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    @StringRes val msgResid: Int = -1
) {

    companion object {

        fun <T> loading(@StringRes msgResid: Int = -1): Resource<T> {
            return Resource(
                LOADING,
                msgResid = msgResid
            )
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data)
        }

        fun <T> error(@StringRes msgResid: Int = -1): Resource<T> {
            return Resource(
                ERROR,
                msgResid = msgResid
            )
        }
    }
}