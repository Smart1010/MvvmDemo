package com.smart1010.module_base.utils

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.smart1010.module_base.room.AppDatabase

/**
 *  author : Snail
 *  date : 2022/11/19 19:37
 *  description : room 工具类
 */
object RoomUtils {

    fun loadDb(application: Application): RoomDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "xRoom")
            .fallbackToDestructiveMigration().build()
    }

    /*  val db: AppDatabase by lazy {
          Room.databaseBuilder(application, AppDatabase::class.java, "xRoom")
  //            .addMigrations(MIGRATION_1_2)
              .fallbackToDestructiveMigration().build()
      }*/

    //数据库升级
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("CREATE TABLE IF NOT EXISTS userinfor (_Id INTEGER NOT NULL PRIMARY KEY,userName TEXT NOT NULL)")
        }
    }
}