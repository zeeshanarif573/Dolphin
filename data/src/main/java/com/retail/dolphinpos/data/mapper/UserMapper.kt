package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.StoreEntity
import com.retail.dolphinpos.data.entities.StoreLogoUrlEntity
import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.domain.model.auth.users.LogoUrl
import com.retail.dolphinpos.domain.model.auth.users.Store
import com.retail.dolphinpos.domain.model.auth.users.User

object UserMapper {

    fun toUserEntity(user: User, password: String): UserEntity {
        return UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            password = password,
            username = user.username,
            pin = user.pin,
            managerId = user.managerId,
            permissions = user.permissions,
            phoneNo = user.phoneNo,
            roleId = user.roleId,
            roleTitle = user.roleTitle,
            status = user.status,
            storeId = user.storeId,
            alreadyClockedIn = user.alreadyClockedIn,
            updatedAt = user.updatedAt,
            createdAt = user.createdAt,
            deletedAt = user.deletedAt,
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
            isAdvertisement = store.isAdvertisement,
            allowCustomDiscount = store.allowCustomDiscount,
            createdAt = store.createdAt,
            deletedAt = store.deletedAt,
            dualPricePercentage = store.dualPricePercentage,
            endTime = store.endTime,
            isMultipleDiscountsAllowed = store.isMultipleDIscountsAllowed,
            startTime = store.startTime,
            status = store.status,
            taxValue = store.taxValue,
            timezone = store.timezone,
            updatedAt = store.updatedAt,
            wpId = store.wpId,
            zipCode = store.zipCode
        )
    }

    fun toStoreLogoUrlEntity(
        userID: Int, storeID: Int, logoUrl: LogoUrl
    ): StoreLogoUrlEntity {
        return StoreLogoUrlEntity(
            userId = userID,
            storeID = storeID,
            fileURL = logoUrl.fileURL,
            originalName = logoUrl.originalName
        )
    }

    fun toUsers(
        userEntity: UserEntity,
        storeEntity: StoreEntity,
        storeLogoUrlEntity: StoreLogoUrlEntity
    ): User {
        return User(
            id = userEntity.id,
            alreadyClockedIn = userEntity.alreadyClockedIn,
            createdAt = userEntity.createdAt,
            deletedAt = userEntity.deletedAt,
            email = userEntity.email,
            password = userEntity.password,
            managerId = userEntity.managerId,
            name = userEntity.name,
            permissions = userEntity.status,
            phoneNo = userEntity.phoneNo,
            pin = userEntity.username,
            roleId = userEntity.roleId,
            roleTitle = userEntity.username,
            status = userEntity.username,
            storeId = userEntity.storeId,
            updatedAt = userEntity.updatedAt,
            username = userEntity.username,
            store = Store(
                id = storeEntity.id,
                name = storeEntity.name,
                location = storeEntity.location,
                multiCashier = storeEntity.multiCashier,
                policy = storeEntity.policy,
                isAdvertisement = storeEntity.isAdvertisement,
                allowCustomDiscount = storeEntity.allowCustomDiscount,
                createdAt = storeEntity.createdAt,
                deletedAt = storeEntity.deletedAt,
                dualPricePercentage = storeEntity.dualPricePercentage,
                endTime = storeEntity.endTime,
                isMultipleDIscountsAllowed = storeEntity.isMultipleDiscountsAllowed,
                startTime = storeEntity.startTime,
                status = storeEntity.status,
                taxValue = storeEntity.taxValue,
                timezone = storeEntity.timezone,
                updatedAt = storeEntity.updatedAt,
                wpId = storeEntity.wpId,
                zipCode = storeEntity.zipCode,
                logoUrl = LogoUrl(
                    fileURL = storeLogoUrlEntity.fileURL,
                    originalName = storeLogoUrlEntity.originalName
                )
            )
        )
    }

}