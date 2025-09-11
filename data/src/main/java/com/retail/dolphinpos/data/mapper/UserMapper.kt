package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.domain.models.login.response.Store
import com.retail.dolphinpos.domain.models.login.response.User

object UserMapper {
    fun userToUserEntity(user: User, password: String): UserEntity {
        return UserEntity(
            id = user.id,
            roleTitle = user.roleTitle,
            status = user.status,
            username = user.username,
            name = user.name,
            password = password,
            storeId = user.storeId,
            managerId = user.managerId
        )
    }

    fun userEntityToUser(userEntity: UserEntity): User {
        return User(
            id = userEntity.id,
            roleTitle = userEntity.roleTitle,
            status = userEntity.status,
            username = userEntity.username,
            name = userEntity.name,
            password = userEntity.password,
            storeId = userEntity.storeId,
            managerId = userEntity.managerId,
            store = null
        )
    }
}