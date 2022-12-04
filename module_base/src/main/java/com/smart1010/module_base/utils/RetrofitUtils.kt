package com.smart1010.module_base.utils

import com.smart1010.module_base.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  author : Snail
 *  date : 2022/12/4 19:46
 *  description : 网络请求
 */
class RetrofitUtils {

    companion object {
        fun getInstance() = RetrofitHelper.single
    }

    object RetrofitHelper {
        val single = RetrofitUtils()
    }


    fun <T> create(clazz: Class<T>): T {
        return createRetrofit().create(clazz)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .readTimeout(5L, TimeUnit.SECONDS)
                    .writeTimeout(5L, TimeUnit.SECONDS)
                    .connectTimeout(5L, TimeUnit.SECONDS)
                    .addInterceptor(HeadInterceptor())
                    .build()
            ).build()

    }

    /**
     * 拦截器
     */
    inner class HeadInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newBuilder = chain.request().newBuilder()

            newBuilder.addHeader("Content-Type", "application/octet-stream")
                .addHeader("token", "")

            val proceed = chain.proceed(newBuilder.build())

            val body = proceed.body()

            return proceed.newBuilder()
                .body(ResponseBody.create(body?.contentType(), body.toString())).build()
        }

    }

}