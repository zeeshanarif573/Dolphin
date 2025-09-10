package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.domain.models.login.response.LoginData

object UserMapper {

    fun loginDetailsToUserEntity(
        loginDetails: LoginData,
        password: String
    ): UserEntity {
        return UserEntity(
            id = loginDetails.user.id,
            accessToken = loginDetails.accessToken,
            refreshToken = loginDetails.refreshToken,
            expiresIn = loginDetails.expiresIn,
            roleTitle = loginDetails.user.roleTitle,
            status = loginDetails.user.status,
            username = loginDetails.user.username,
            name = loginDetails.user.name,
            password = password,
            storeId = loginDetails.user.storeId,
            managerId = loginDetails.user.managerId
        )
    }

}