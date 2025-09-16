package com.retail.dolphinpos.domain.model.auth.pin.request

data class VerifyPinRequest(
    val pin : String,
    val rootManagerId : Int,
    val storeId : Int,
    val storeRegisterId: Int
)