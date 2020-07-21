package com.starcodex.olimpia.util


enum class ValidationRules constructor (val param: String? = null) {
    NO_EMPTY(),
    LENGTH(),
    EMAIL()
}