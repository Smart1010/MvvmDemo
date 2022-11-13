package com.smart1010.mvvmdemo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MvvmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}