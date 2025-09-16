package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.store_register_response.StoreRegistersEntity
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.GetStoreRegistersData

object StoreRegisterMapper {

    fun toStoreRegisterEntity(
        userID: Int, storeRegistersData: GetStoreRegistersData
    ): StoreRegistersEntity {
        return StoreRegistersEntity(
            createdAt = storeRegistersData.createdAt,
            id = storeRegistersData.id,
            userId = userID,
            name = storeRegistersData.name,
            storeId = storeRegistersData.storeId,
            updatedAt = storeRegistersData.updatedAt
        )
    }

    fun toStorageRegisterData(storeRegistersEntity: StoreRegistersEntity): GetStoreRegistersData {
        return GetStoreRegistersData(
            createdAt = storeRegistersEntity.createdAt,
            id = storeRegistersEntity.id,
            name = storeRegistersEntity.name,
            storeId = storeRegistersEntity.storeId,
            updatedAt = storeRegistersEntity.updatedAt
        )
    }
}