package com.retail.dolphinpos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.retail.dolphinpos.data.entities.verify_pin_response.VerifyPinLogoUrlEntity
import com.retail.dolphinpos.data.entities.verify_pin_response.VerifyPinStoreEntity
import com.retail.dolphinpos.data.entities.verify_pin_response.VerifyPinUserEntity

@Dao
interface VerifyPinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerifyPinUser(verifyPinUserEntity: VerifyPinUserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerifyPinStoreDetails(verifyPinStoreEntity: VerifyPinStoreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerifyPinStoreLogoUrlDetails(verifyPinLogoUrlEntity: VerifyPinLogoUrlEntity): Long


    @Query("SELECT * FROM user_table WHERE id = :userId")
    suspend fun getVerifyPinUserEntity(userId: Int): VerifyPinUserEntity?

    @Query("SELECT * FROM store WHERE id = :storeId")
    suspend fun getVerifyPinStoreEntity(storeId: Int): VerifyPinStoreEntity?

    @Query("SELECT * FROM store_logo_url WHERE storeID = :storeId")
    suspend fun getVerifyPinLogoUrlEntity(storeId: Int): VerifyPinLogoUrlEntity?

    @Transaction
    suspend fun insertCompleteVerifyPinUserData(
        verifyPinUserEntity: VerifyPinUserEntity,
        verifyPinStoreEntity: VerifyPinStoreEntity,
        verifyPinLogoUrlEntity: VerifyPinLogoUrlEntity
    ) {
        insertVerifyPinUser(verifyPinUserEntity)
        insertVerifyPinStoreDetails(verifyPinStoreEntity)
        insertVerifyPinStoreLogoUrlDetails(verifyPinLogoUrlEntity)
    }

}