package com.smart1010.module_base.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

/**
 *  author : Snail
 *  date : 2022/11/13 17:31
 *  description : activity 管理类
 */
class ActivityMannagerUtils {

    companion object {
        fun getInstance() = InstanceHelper.single
    }

    object InstanceHelper {
        val single = ActivityMannagerUtils()
    }

    var activityStack: Stack<Activity>? = null

    //添加
    fun addActivity(activity: Activity) {
        if (null == activityStack)
            activityStack = Stack<Activity>()
        activityStack?.add(activity)
    }

    //获取当前Activity
    fun currentActivity(): Activity? {
        return activityStack?.lastElement()
    }

    //结束当前Activity
    fun finishActivity() {
        activityStack?.let {
            finishActivity(it.lastElement())
        }
    }

    //结束指定的Activity
    fun finishActivity(activity: Activity) {
        activityStack?.let {
            it.remove(activity)
            activity.finish()
        }
    }

    //结束所有Activity
    fun finishAllActivity() {
        activityStack?.let {
            it.forEach { activity ->
                activity.finish()
            }
            it.clear()
        }
    }

    //退出应用程序
    fun exitApp(context: Context) {
        finishActivity()
        val activityMannager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityMannager.restartPackage(context.packageName)
        exitProcess(0)
    }
}