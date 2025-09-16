package com.retail.dolphinpos.domain.model.auth.pin.response

data class VerifyPinResponse(
    val message: String,
    val user: VerifyPinUser
)