package com.retail.dolphinpos.data.entities.verify_pin_response

data class VerifyPinLogoUrlEntity(
    val userId: Int,
    val storeID: Int,
    val fileURL: String,
    val originalName: String
)