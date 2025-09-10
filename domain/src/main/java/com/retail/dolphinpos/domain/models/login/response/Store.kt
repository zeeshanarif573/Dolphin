package com.retail.dolphinpos.domain.models.login.response

data class Store(
    val advertisementImg: Any,
    val allowCustomDiscount: Boolean,
    val id: Int,
    val isAdvertisement: Any,
    val location: String,
    val logoUrl: LogoUrl,
    val multiCashier: Boolean,
    val name: String,
    val policy: String
)