package com.retail.dolphinpos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.retail.dolphinpos.data.entities.StoreEntity
import com.retail.dolphinpos.data.entities.StoreLogoUrlEntity
import com.retail.dolphinpos.data.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoreDetails(storeEntity: StoreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoreLogoUrlDetails(storeLogoUrlEntity: StoreLogoUrlEntity): Long


    @Query("SELECT * FROM user WHERE pin = :pin")
    suspend fun getUserEntity(pin: String): UserEntity?

    @Query("SELECT * FROM store WHERE id = :storeId")
    suspend fun getStoreEntity(storeId: Int): StoreEntity?

    @Query("SELECT * FROM store_logo_url WHERE storeID = :storeId")
    suspend fun getLogoUrlEntity(storeId: Int): StoreLogoUrlEntity?

}