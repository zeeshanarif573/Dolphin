package com.retail.dolphinpos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.retail.dolphinpos.data.entities.StoreLogoUrlEntity

@Dao
interface StoreLogoUrlDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoreLogoUrl(storeLogoUrlEntity: StoreLogoUrlEntity)

    @Query("SELECT * FROM store_logo_url")
    suspend fun getStoreLogoUrl(): StoreLogoUrlEntity?
}