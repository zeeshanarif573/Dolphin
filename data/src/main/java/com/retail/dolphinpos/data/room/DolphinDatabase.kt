package com.retail.dolphinpos.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class DolphinDatabase : RoomDatabase() {

    abstract fun userLoginDetailsDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DolphinDatabase? = null

        fun getDatabase(context: Context): DolphinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DolphinDatabase::class.java,
                    "dolphin_retail_pos_db"
                ).addCallback(object : Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        db.execSQL("PRAGMA foreign_keys = ON;")
                    }
                })
                    //.addMigrations(MIGRATION_47_48)
                    //.fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

//        val MIGRATION_47_48 = object : Migration(47, 48) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                db.execSQL("ALTER TABLE products ADD COLUMN sku TEXT")
//            }
//        }

        // Method to delete the database file
//        fun deleteDatabase(context: Context) {
//            context.deleteDatabase("lingerie_pos_local_database")
//            INSTANCE = null // Reset the INSTANCE so Room can recreate it when needed
//        }

    }
}