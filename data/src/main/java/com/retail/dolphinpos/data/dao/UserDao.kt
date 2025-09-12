package com.retail.dolphinpos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.retail.dolphinpos.data.entities.AuthEntity
import com.retail.dolphinpos.data.entities.StoreEntity
import com.retail.dolphinpos.data.entities.StoreLogoUrlEntity
import com.retail.dolphinpos.data.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAuthDetails(authEntity: AuthEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStoreDetails(storeEntity: StoreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoreLogoUrlDetails(storeLogoUrlEntity: StoreLogoUrlEntity): Long

    @Query("SELECT * FROM auth_table WHERE userId = :userId")
    suspend fun getAuthEntity(userId: Int): AuthEntity?

    @Query("SELECT * FROM user_table WHERE id = :userId")
    suspend fun getUserEntity(userId: Int): UserEntity?

    @Query("SELECT * FROM store WHERE id = :storeId")
    suspend fun getStoreEntity(storeId: Int): StoreEntity?

    @Query("SELECT * FROM store_logo_url WHERE storeID = :storeId")
    suspend fun getStoreLogoUrlEntity(storeId: Int): StoreLogoUrlEntity?

    @Transaction
    suspend fun insertCompleteUserData(
        userEntity: UserEntity,
        authEntity: AuthEntity,
        storeEntity: StoreEntity,
        storeLogoUrlEntity: StoreLogoUrlEntity
    ) {
        insertUser(userEntity)
        insertUserAuthDetails(authEntity)
        insertUserStoreDetails(storeEntity)
        insertStoreLogoUrlDetails(storeLogoUrlEntity)
    }

}