package com.starcodex.olimpia.util.arch

data class Event<out T>(private val data: T) {

    var hasBeenConsumed = false
        private set

    fun consume(): T? =
        if (hasBeenConsumed) {
            null
        } else {
            hasBeenConsumed = true
            data
        }
}