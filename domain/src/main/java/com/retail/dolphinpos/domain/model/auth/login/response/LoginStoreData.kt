package com.retail.dolphinpos.domain.model.auth.login.response

data class LoginStoreData(
    val advertisementImg: String?,
    val allowCustomDiscount: Boolean,
    val id: Int,
    val isAdvertisement: Boolean?,
    val location: String,
    val logoUrl: LoginStoreLogoUrlData?,
    val multiCashier: Boolean,
    val name: String,
    val policy: String
)