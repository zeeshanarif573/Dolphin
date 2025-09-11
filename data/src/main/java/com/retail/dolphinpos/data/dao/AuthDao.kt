package com.retail.dolphinpos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.retail.dolphinpos.data.entities.AuthEntity

@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuth(authEntity: AuthEntity)

    @Query("SELECT * FROM auth_table")
    suspend fun getAuth(): AuthEntity?
}