package com.retail.dolphinpos.domain.pin

data class VerifyPinRequest(
    val pin : String,
    val rootManagerId : Int,
    val storeId : Int,
    val storeRegisterId: Int
)