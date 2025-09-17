package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.StoreEntity
import com.retail.dolphinpos.data.entities.StoreLogoUrlEntity
import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinLogoUrl
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinStore
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinUser

object UserMapper {

    fun toUserEntity(verifyPinUser: VerifyPinUser, password: String): UserEntity {
        return UserEntity(
            id = verifyPinUser.id,
            name = verifyPinUser.name,
            email = verifyPinUser.email,
            password = password,
            username = verifyPinUser.username,
            pin = verifyPinUser.pin,
            managerId = verifyPinUser.managerId,
            permissions = verifyPinUser.permissions,
            phoneNo = verifyPinUser.phoneNo,
            roleId = verifyPinUser.roleId,
            roleTitle = verifyPinUser.roleTitle,
            status = verifyPinUser.status,
            storeId = verifyPinUser.storeId,
            alreadyClockedIn = verifyPinUser.alreadyClockedIn,
            updatedAt = verifyPinUser.updatedAt,
            createdAt = verifyPinUser.createdAt,
            deletedAt = verifyPinUser.deletedAt,
        )
    }

    fun toStoreEntity(userID: Int, verifyPinStore: VerifyPinStore): StoreEntity {
        return StoreEntity(
            id = verifyPinStore.id,
            userId = userID,
            name = verifyPinStore.name,
            location = verifyPinStore.location,
            multiCashier = verifyPinStore.multiCashier,
            policy = verifyPinStore.policy,
            isAdvertisement = verifyPinStore.isAdvertisement,
            allowCustomDiscount = verifyPinStore.allowCustomDiscount,
            createdAt = verifyPinStore.createdAt,
            deletedAt = verifyPinStore.deletedAt,
            dualPricePercentage = verifyPinStore.dualPricePercentage,
            endTime = verifyPinStore.endTime,
            isMultipleDiscountsAllowed = verifyPinStore.isMultipleDIscountsAllowed,
            startTime = verifyPinStore.startTime,
            status = verifyPinStore.status,
            taxValue = verifyPinStore.taxValue,
            timezone = verifyPinStore.timezone,
            updatedAt = verifyPinStore.updatedAt,
            wpId = verifyPinStore.wpId,
            zipCode = verifyPinStore.zipCode
        )
    }

    fun toStoreLogoUrlEntity(
        userID: Int, storeID: Int, verifyPinLogoUrl: VerifyPinLogoUrl
    ): StoreLogoUrlEntity {
        return StoreLogoUrlEntity(
            userId = userID,
            storeID = storeID,
            fileURL = verifyPinLogoUrl.fileURL,
            originalName = verifyPinLogoUrl.originalName
        )
    }

    fun toVerifyPinUser(
        userEntity: UserEntity,
        storeEntity: StoreEntity,
        storeLogoUrlEntity: StoreLogoUrlEntity
    ): VerifyPinUser {
        return VerifyPinUser(
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
            store = VerifyPinStore(
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
                logoUrl = VerifyPinLogoUrl(
                    fileURL = storeLogoUrlEntity.fileURL,
                    originalName = storeLogoUrlEntity.originalName
                )
            )
        )
    }

}