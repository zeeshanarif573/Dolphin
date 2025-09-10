package com.retail.dolphinpos.domain.models.login.response

data class LoginData(
    val accessToken: String,
    val expiresIn: String,
    val refreshToken: String,
    val user: User
)