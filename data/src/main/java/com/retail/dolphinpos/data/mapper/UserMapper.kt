package com.retail.dolphinpos.data.mapper

import com.retail.dolphinpos.data.entities.LocationEntity
import com.retail.dolphinpos.data.entities.RegisterEntity
import com.retail.dolphinpos.data.entities.StoreEntity
import com.retail.dolphinpos.data.entities.StoreLogoUrlEntity
import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.domain.model.auth.login.response.AllStoreUsers
import com.retail.dolphinpos.domain.model.auth.login.response.Locations
import com.retail.dolphinpos.domain.model.auth.login.response.Registers
import com.retail.dolphinpos.domain.model.auth.login.response.Store
import com.retail.dolphinpos.domain.model.auth.login.response.StoreLogoUrl

object UserMapper {

    // -------------------------
    // Domain → Entity Mappers
    // -------------------------

    fun toUserEntity(user: AllStoreUsers, password: String): UserEntity {
        return UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            username = user.username,
            password = password,
            pin = user.pin,
            status = user.status,
            phoneNo = user.phoneNo,
            storeId = user.storeId,
            locationId = user.locationId,
            roleId = user.roleId,
            roleTitle = user.roleTitle,
        )
    }

    fun toStoreEntity(userID: Int, store: Store): StoreEntity {
        return StoreEntity(
            id = store.id,
            userID = userID,
            name = store.name,
            address = store.address,
            multiCashier = store.multiCashier,
            policy = store.policy,
            advertisementImg = store.advertisementImg,
            isAdvertisement = store.isAdvertisement
        )
    }


    fun toStoreLogoUrlEntity(
        storeID: Int, logoUrl: StoreLogoUrl
    ): StoreLogoUrlEntity {
        return StoreLogoUrlEntity(
            storeID = storeID,
            alt = logoUrl.alt,
            original = logoUrl.original,
            thumbnail = logoUrl.thumbnail
        )
    }


    fun toLocationEntity(storeID: Int, location: Locations): LocationEntity {
        return LocationEntity(
            id = location.id,
            storeID = storeID,
            name = location.name,
            address = location.address,
            status = location.status,
            zipCode = location.zipCode,
            taxValue = location.taxValue,
            taxTitle = location.taxTitle,
            startTime = location.startTime,
            endTime = location.endTime,
            multiCashier = location.multiCashier
        )
    }

    fun toRegisterEntity(register: Registers): RegisterEntity {
        return RegisterEntity(
            id = register.id,
            name = register.name,
            status = register.status,
            locationId = register.locationId
        )
    }

    // -------------------------
    // Entity → Domain Mappers
    // -------------------------

    fun toUsers(userEntity: UserEntity): AllStoreUsers {
        return AllStoreUsers(
            id = userEntity.id,
            name = userEntity.name,
            email = userEntity.email,
            username = userEntity.username,
            password = userEntity.password,
            pin = userEntity.pin,
            status = userEntity.status,
            phoneNo = userEntity.phoneNo,
            storeId = userEntity.storeId,
            locationId = userEntity.locationId,
            roleId = userEntity.roleId,
            roleTitle = userEntity.roleTitle,
        )
    }

    fun toStore(
        storeEntity: StoreEntity,
        storeLogoUrlEntity: StoreLogoUrlEntity?,
        locationEntities: List<LocationEntity>,
        registerEntities: List<RegisterEntity>
    ): Store {
        return Store(
            id = storeEntity.id,
            name = storeEntity.name,
            address = storeEntity.address,
            multiCashier = storeEntity.multiCashier,
            policy = storeEntity.policy,
            advertisementImg = storeEntity.advertisementImg,
            isAdvertisement = storeEntity.isAdvertisement,
            logoUrl = toStoreLogoUrl(storeLogoUrlEntity),
            locations = toLocations(locationEntities, registerEntities)
        )
    }

    fun toStoreLogoUrl(storeLogoUrlEntity: StoreLogoUrlEntity?): StoreLogoUrl {
        return StoreLogoUrl(
            alt = storeLogoUrlEntity?.alt,
            original = storeLogoUrlEntity?.original,
            thumbnail = storeLogoUrlEntity?.thumbnail
        )
    }

    fun toLocations(
        locationEntities: List<LocationEntity>,
        registerEntities: List<RegisterEntity>
    ): List<Locations> {
        return locationEntities.map { location ->
            Locations(
                id = location.id,
                name = location.name,
                address = location.address,
                status = location.status,
                zipCode = location.zipCode,
                taxValue = location.taxValue,
                taxTitle = location.taxTitle,
                startTime = location.startTime,
                endTime = location.endTime,
                multiCashier = location.multiCashier,
                registers = registerEntities
                    .filter { it.locationId == location.id }
                    .map { toRegister(it) }
            )
        }
    }

    private fun toRegister(register: RegisterEntity): Registers {
        return Registers(
            id = register.id,
            name = register.name,
            status = register.status,
            locationId = register.locationId
        )
    }

    fun toLocationsAgainstStoreID(entities: List<LocationEntity>): List<Locations> {
        return entities.map { entity ->
            Locations(
                id = entity.id,
                name = entity.name,
                address = entity.address,
                status = entity.status,
                zipCode = entity.zipCode,
                taxValue = entity.taxValue,
                taxTitle = entity.taxTitle,
                startTime = entity.startTime,
                endTime = entity.endTime,
                multiCashier = entity.multiCashier,
                registers = emptyList()
            )
        }
    }


    fun toRegistersAgainstLocationID(
        locationID: Int,
        entities: List<RegisterEntity>
    ): List<Registers> {
        return entities.map { entity ->
            Registers(
                id = entity.id,
                name = entity.name,
                status = entity.status,
                locationId = locationID
            )
        }
    }

}