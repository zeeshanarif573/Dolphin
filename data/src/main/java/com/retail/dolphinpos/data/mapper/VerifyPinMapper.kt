package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.verify_pin_response.VerifyPinLogoUrlEntity
import com.retail.dolphinpos.data.entities.verify_pin_response.VerifyPinStoreEntity
import com.retail.dolphinpos.data.entities.verify_pin_response.VerifyPinUserEntity
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinLogoUrl
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinStore
import com.retail.dolphinpos.domain.model.auth.pin.response.VerifyPinUser

object VerifyPinMapper {

    fun toVerifyPinUserEntity(verifyPinUser: VerifyPinUser, password: String): VerifyPinUserEntity {
        return VerifyPinUserEntity(
            id = verifyPinUser.id,
            alreadyClockedIn = verifyPinUser.alreadyClockedIn,
            createdAt = verifyPinUser.createdAt,
            deletedAt = verifyPinUser.id,
            email = verifyPinUser.email,
            password = password,
            managerId = verifyPinUser.managerId,
            name = verifyPinUser.name,
            permissions = verifyPinUser.status,
            phoneNo = verifyPinUser.phoneNo,
            pin = verifyPinUser.username,
            roleId = verifyPinUser.roleId,
            roleTitle = verifyPinUser.username,
            status = verifyPinUser.username,
            storeId = verifyPinUser.storeId,
            updatedAt = verifyPinUser.username,
            username = verifyPinUser.username
        )
    }

    fun toVerifyPinStoreEntity(userID: Int, verifyPinStore: VerifyPinStore): VerifyPinStoreEntity {
        return VerifyPinStoreEntity(
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

    fun toVerifyPinStoreLogoUrlEntity(
        userID: Int, storeID: Int, verifyPinLogoUrl: VerifyPinLogoUrl
    ): VerifyPinLogoUrlEntity {
        return VerifyPinLogoUrlEntity(
            userId = userID,
            storeID = storeID,
            fileURL = verifyPinLogoUrl.fileURL,
            originalName = verifyPinLogoUrl.originalName
        )
    }

    fun toVerifyPinData(
        verifyPinUserEntity: VerifyPinUserEntity,
        verifyPinStoreEntity: VerifyPinStoreEntity,
        verifyPinLogoUrlEntity: VerifyPinLogoUrlEntity
    ): VerifyPinUser {
        return VerifyPinUser(
            id = verifyPinUserEntity.id,
            alreadyClockedIn = verifyPinUserEntity.alreadyClockedIn,
            createdAt = verifyPinUserEntity.createdAt,
            deletedAt = verifyPinUserEntity.id,
            email = verifyPinUserEntity.email,
            password = verifyPinUserEntity.password,
            managerId = verifyPinUserEntity.managerId,
            name = verifyPinUserEntity.name,
            permissions = verifyPinUserEntity.status,
            phoneNo = verifyPinUserEntity.phoneNo,
            pin = verifyPinUserEntity.username,
            roleId = verifyPinUserEntity.roleId,
            roleTitle = verifyPinUserEntity.username,
            status = verifyPinUserEntity.username,
            storeId = verifyPinUserEntity.storeId,
            updatedAt = verifyPinUserEntity.updatedAt,
            username = verifyPinUserEntity.username,
            store = VerifyPinStore(
                id = verifyPinStoreEntity.id,
                name = verifyPinStoreEntity.name,
                location = verifyPinStoreEntity.location,
                multiCashier = verifyPinStoreEntity.multiCashier,
                policy = verifyPinStoreEntity.policy,
                isAdvertisement = verifyPinStoreEntity.isAdvertisement,
                allowCustomDiscount = verifyPinStoreEntity.allowCustomDiscount,
                createdAt = verifyPinStoreEntity.createdAt,
                deletedAt = verifyPinStoreEntity.deletedAt,
                dualPricePercentage = verifyPinStoreEntity.dualPricePercentage,
                endTime = verifyPinStoreEntity.endTime,
                isMultipleDIscountsAllowed = verifyPinStoreEntity.isMultipleDiscountsAllowed,
                startTime = verifyPinStoreEntity.startTime,
                status = verifyPinStoreEntity.status,
                taxValue = verifyPinStoreEntity.taxValue,
                timezone = verifyPinStoreEntity.timezone,
                updatedAt = verifyPinStoreEntity.updatedAt,
                wpId = verifyPinStoreEntity.wpId,
                zipCode = verifyPinStoreEntity.zipCode,
                logoUrl = VerifyPinLogoUrl(
                    fileURL = verifyPinLogoUrlEntity.fileURL,
                    originalName = verifyPinLogoUrlEntity.originalName
                )
            )
        )
    }

}