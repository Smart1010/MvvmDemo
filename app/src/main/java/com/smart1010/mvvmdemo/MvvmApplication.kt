package com.smart1010.mvvmdemo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV

class MvvmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化mmkv
        MMKV.initialize(this)

        //初始化路由
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}