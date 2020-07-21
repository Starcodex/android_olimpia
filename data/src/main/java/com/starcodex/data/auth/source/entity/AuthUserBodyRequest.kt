package com.starcodex.data.auth.source.entity

class AuthUserBodyRequest(
    var email: String? = null,
    var password: String? = null,
    var password_confirmation: String? = null
)