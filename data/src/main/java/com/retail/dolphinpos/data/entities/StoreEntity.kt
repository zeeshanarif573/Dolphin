package com.retail.dolphinpos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store")

data class StoreEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val name: String?,
    val location: String?,
    val multiCashier: Boolean?,
    val policy: String?,
    val isAdvertisement: Boolean?,
    val allowCustomDiscount: Boolean?,
    val createdAt: String?,
    val deletedAt: String?,
    val dualPricePercentage: String?,
    val endTime: String?,
    val isMultipleDiscountsAllowed: Boolean?,
    val startTime: String?,
    val status: String?,
    val taxValue: Int?,
    val timezone: String?,
    val updatedAt: String?,
    val wpId: Int?,
    val zipCode: String?
)
