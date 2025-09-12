package com.retail.dolphinpos.data.entities.auth.login_response

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
data class StoreEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val name: String,
    val location: String,
    val multiCashier: Boolean,
    val policy: String,
    val advertisementImg: String?,
    val isAdvertisement: Boolean?,
    val allowCustomDiscount: Boolean
)
