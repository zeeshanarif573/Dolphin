package com.retail.dolphinpos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.retail.dolphinpos.data.entities.user.ActiveUserDetailsEntity
import com.retail.dolphinpos.data.entities.user.BatchEntity
import com.retail.dolphinpos.data.entities.user.LocationEntity
import com.retail.dolphinpos.data.entities.user.RegisterEntity
import com.retail.dolphinpos.data.entities.user.StoreEntity
import com.retail.dolphinpos.data.entities.user.StoreLogoUrlEntity
import com.retail.dolphinpos.data.entities.user.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoreDetails(storeEntity: StoreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoreLogoUrlDetails(storeLogoUrlEntity: StoreLogoUrlEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locationEntity: LocationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegisters(registerEntity: RegisterEntity): Long

    @Query("SELECT * FROM user WHERE pin = :pin")
    suspend fun getUserByPin(pin: String): UserEntity?

    @Query("SELECT * FROM store")
    suspend fun getStore(): StoreEntity

    @Query("SELECT * FROM store_logo_url WHERE storeID = :storeId")
    suspend fun getStoreLogoUrl(storeId: Int?): StoreLogoUrlEntity?

    @Query("SELECT * FROM store_locations WHERE storeID = :storeId")
    suspend fun getLocationsByStoreId(storeId: Int?): List<LocationEntity>

    @Query("SELECT * FROM location_registers WHERE locationID = :locationID")
    suspend fun getRegistersByLocationId(locationID: Int?): List<RegisterEntity>

    @Query("SELECT * FROM store_locations WHERE id = :locationID")
    suspend fun getLocationByLocationId(locationID: Int?): LocationEntity

    @Query("SELECT * FROM location_registers WHERE id = :registerID")
    suspend fun getRegisterByRegisterId(registerID: Int?): RegisterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActiveUserDetails(activeUserDetailsEntity: ActiveUserDetailsEntity)

    @Query("SELECT * FROM active_user_details WHERE pin = :pin")
    suspend fun getActiveUserDetailsByPin(pin: String): ActiveUserDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatchDetails(batchEntity: BatchEntity)

    @Query("SELECT * FROM batch")
    suspend fun getBatchDetails(): BatchEntity

}