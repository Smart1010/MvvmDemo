package com.smart1010.module_base.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 *  author : Snail
 *  date : 2022/11/15 21:37
 *  description :
 */
object MMKVUtils {

    /**
     * 存储数据
     */
    fun put(key: String, value: Any) {
        val mmkv = MMKV.defaultMMKV()
        if (value is String) {
            mmkv.encode(key, value)
        } else if (value is Double) {
            mmkv.encode(key, value)
        } else if (value is Float) {
            mmkv.encode(key, value)
        } else if (value is Int) {
            mmkv.encode(key, value)
        } else if (value is Parcelable) {
            mmkv.encode(key, value)
        } else if (value is ByteArray) {
            mmkv.encode(key, value)
        } else if (value is Long) {
            mmkv.encode(key, value)
        } else if (value is Boolean) {
            mmkv.encode(key, value)
        }
    }

    /**
     * 获取数据
     */
    fun get(key: String, value: Any): Any? {
        val mmkv = MMKV.defaultMMKV()
        if (value is String) {
            return mmkv.decodeString(key, value)
        }
        if (value is Double) {
            return mmkv.decodeDouble(key, value)
        }
        if (value is Float) {
            return mmkv.decodeFloat(key, value)
        }
        if (value is Int) {
            return mmkv.decodeInt(key, value)
        }
        if (value is Class<*>) {
            return mmkv.decodeParcelable(key, value as Class<Parcelable>)
        }
        if (value is ByteArray) {
            return mmkv.decodeBytes(key, value)
        }
        if (value is Long) {
            return mmkv.decodeLong(key, value)
        }
        if (value is Boolean) {
            return mmkv.decodeBool(key, value)
        }
        return null
    }

//    fun getParcelable(key: String, clazz: Class<Parcelable>): Parcelable? {
//        var mmkv = MMKV.defaultMMKV()
//        return mmkv.decodeParcelable(key, clazz) ?: null
//    }

    //   删除数据
    fun remove(key: String) {
        val mmkv = MMKV.defaultMMKV()
        mmkv.removeValueForKey(key)
    }

    // 删除多个数据
    fun remove(vararg key: String) {
        val mmkv = MMKV.defaultMMKV()
        val keys = arrayOfNulls<String>(key.size)
        key.forEachIndexed { index, content ->
            keys.set(index, content)
        }
        mmkv.removeValuesForKeys(keys)
    }

    // 清理所有数据
    fun clearAll() {
        MMKV.defaultMMKV().clearAll()
    }
}