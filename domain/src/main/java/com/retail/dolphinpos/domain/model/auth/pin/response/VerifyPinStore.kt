package com.retail.dolphinpos.domain.model.auth.pin.response

data class VerifyPinStore(
    val allowCustomDiscount: Boolean?,
    val createdAt: String?,
    val deletedAt: String?,
    val dualPricePercentage: String?,
    val endTime: String?,
    val id: Int,
    val isAdvertisement: Boolean?,
    val isMultipleDIscountsAllowed: Boolean?,
    val location: String?,
    val logoUrl: VerifyPinLogoUrl,
    val multiCashier: Boolean?,
    val name: String?,
    val policy: String?,
    val startTime: String?,
    val status: String?,
    val taxValue: Int?,
    val timezone: String?,
    val updatedAt: String?,
    val wpId: Int?,
    val zipCode: String?
)