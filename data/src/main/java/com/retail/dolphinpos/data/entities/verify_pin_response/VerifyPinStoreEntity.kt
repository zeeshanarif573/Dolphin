package com.retail.dolphinpos.data.entities.verify_pin_response

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.retail.dolphinpos.data.entities.login_response.UserEntity

@Entity(
    tableName = "store",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class VerifyPinStoreEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val name: String,
    val location: String,
    val multiCashier: Boolean,
    val policy: String,
    val isAdvertisement: Boolean,
    val allowCustomDiscount: Boolean,
    val createdAt: String,
    val deletedAt: Any,
    val dualPricePercentage: String,
    val endTime: String,
    val isMultipleDiscountsAllowed: Boolean,
    val startTime: String,
    val status: String,
    val taxValue: Int,
    val timezone: String,
    val updatedAt: String,
    val wpId: Int,
    val zipCode: String
)
