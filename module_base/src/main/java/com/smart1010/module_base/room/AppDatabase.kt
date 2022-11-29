package com.smart1010.module_base.room

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import com.smart1010.module_base.utils.RoomUtils

/**
 *  author : Snail
 *  date : 2022/11/19 19:48
 *  description :
 */
@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * 单列获取database对象
     */
    companion object {
        private var instance: AppDatabase? = null

        fun get(application: Application): AppDatabase {
            if (null == instance) {
                synchronized(AppDatabase::class.java) {
                    if (null == instance) {
                        instance = RoomUtils.loadDb(application) as AppDatabase
                    }
                }
            }
            return instance!!
        }
    }

    abstract fun userDao(): UserDao
}