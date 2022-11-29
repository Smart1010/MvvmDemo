package com.smart1010.module_base.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 *  author : Snail
 *  date : 2022/11/19 19:43
 *  description :
 */
@Dao
interface UserDao {

    @Query("select * from user")
    suspend fun getAllUser(): List<User>

    @Insert
    fun insertUser(vararg users: User)

    @Delete
    fun deleteUser(user: User)

}