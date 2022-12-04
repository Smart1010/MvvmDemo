package com.smart1010.module_base

import com.smart1010.module_base.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *  author : Snail
 *  date : 2022/12/4 20:01
 *  description :
 */
interface ApiService {

    //登录
    @POST("/account/login")
    suspend fun login(@Body body: RequestBody): BaseResponse<Any>

}