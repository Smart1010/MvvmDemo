package com.smart1010.module_base.base

/**
 *  author : Snail
 *  date : 2022/11/13 17:16
 *  description : 网络数据封装基类
 */
data class BaseResponse<T>(
    val code: String?,
    val message: String?,
    val data: T?
)

