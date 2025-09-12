package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.AuthEntity
import com.retail.dolphinpos.data.entities.StoreEntity
import com.retail.dolphinpos.data.entities.StoreLogoUrlEntity
import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.domain.models.login.response.LoginData
import com.retail.dolphinpos.domain.models.login.response.StoreLogoUrl
import com.retail.dolphinpos.domain.models.login.response.Store
import com.retail.dolphinpos.domain.models.login.response.User

object UserMapper {

    fun toAuthEntity(loginData: LoginData): AuthEntity {
        return AuthEntity(
            userId = loginData.user.id,
            accessToken = loginData.accessToken,
            refreshToken = loginData.refreshToken,
            expiresIn = loginData.expiresIn,
        )
    }

    fun toUserEntity(user: User, password: String): UserEntity {
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

    fun toStoreEntity(userID: Int, store: Store): StoreEntity {
        return StoreEntity(
            id = store.id,
            userId = userID,
            name = store.name,
            location = store.location,
            multiCashier = store.multiCashier,
            policy = store.policy,
            advertisementImg = store.advertisementImg,
            isAdvertisement = store.isAdvertisement,
            allowCustomDiscount = store.allowCustomDiscount,
        )
    }

    fun toStoreLogoUrlEntity(
        userID: Int, storeID: Int, storeLogoUrl: StoreLogoUrl
    ): StoreLogoUrlEntity {
        return StoreLogoUrlEntity(
            userId = userID,
            storeID = storeID,
            fileURL = storeLogoUrl.fileURL,
            originalName = storeLogoUrl.originalName
        )
    }

    fun userCompleteDataToLoginData(
        userEntity: UserEntity,
        authEntity: AuthEntity,
        storeEntity: StoreEntity,
        storeLogoUrlEntity: StoreLogoUrlEntity
    ): LoginData {
        return LoginData(
            accessToken = authEntity.accessToken,
            refreshToken = authEntity.refreshToken,
            expiresIn = authEntity.expiresIn,
            user = User(
                id = userEntity.id,
                name = userEntity.name,
                username = userEntity.username,
                password = userEntity.password,
                roleTitle = userEntity.roleTitle,
                status = userEntity.status,
                storeId = userEntity.storeId,
                managerId = userEntity.managerId,
                store = Store(
                    id = storeEntity.id,
                    name = storeEntity.name,
                    location = storeEntity.location,
                    multiCashier = storeEntity.multiCashier,
                    policy = storeEntity.policy,
                    advertisementImg = storeEntity.advertisementImg,
                    isAdvertisement = storeEntity.isAdvertisement,
                    allowCustomDiscount = storeEntity.allowCustomDiscount,
                    logoUrl = StoreLogoUrl(
                        fileURL = storeLogoUrlEntity.fileURL,
                        originalName = storeLogoUrlEntity.originalName
                    )
                )
            )
        )
    }

}