package com.retail.dolphinpos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.retail.dolphinpos.data.entities.StoreEntity

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(storeEntity: StoreEntity)

    @Query("SELECT * FROM store")
    suspend fun getStore(): StoreEntity?

}