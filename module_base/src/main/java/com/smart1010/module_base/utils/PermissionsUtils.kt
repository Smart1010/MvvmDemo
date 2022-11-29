package com.smart1010.module_base.utils

import android.content.Context
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions

/**
 *  author : Snail
 *  date : 2022/11/20 15:30
 *  description : 权限申请
 */
object PermissionsUtils {

    fun requestPermissions(
        context: Context,
        onGranted: OnPermissionBack,
        vararg permissions: String
    ) {
        XXPermissions.with(context)
            .permission(permissions)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                    if (all) onGranted.onGranted()
                }

                override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                    super.onDenied(permissions, never)
                    if (never)// 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(context)
                }
            })
    }

    interface OnPermissionBack {
        /**
         * 有权限被同意授予时回调
         *
         * @param permissions           请求成功的权限组
         * @param all                   是否全部授予了
         */
        fun onGranted()

    }

}