package com.smart1010.module_base.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *  author : Snail
 *  date : 2022/11/19 19:39
 *  description : room实体类
 */
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var _id: Int = 0,
    val name: String,
    val age: Int,
    val sex: String

)
