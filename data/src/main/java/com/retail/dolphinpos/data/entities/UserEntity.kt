package com.retail.dolphinpos.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.retail.dolphinpos.domain.models.login.response.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey var id: Int,
    val accessToken: String? = "",
    val refreshToken: String? = "",
    val expiresIn: String? = "",
    val storeId: Int? = 0,
    val name: String? = "",
    val phoneNo: String? = "",
    val email: String? = "",
    val username: String? = "",
    val password: String? = "",
    val managerId: Int? = 0,
    val pin: String? = "",
    val status: String? = "",
    val roleId: Int? = 0,
    val roleTitle: String? = "",
    val createdAt: String? = "",
    val updatedAt: String? = "",
    var isSynced: Boolean = false
)

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        roleTitle = roleTitle,
        status = status,
        username = username,
        name = name,
        password = password,
        storeId = storeId,
        managerId = managerId
    )
}


