package com.smart1010.mvvmdemo.activity

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.smart1010.module_base.base.BaseViewModel
import com.smart1010.module_base.room.AppDatabase
import com.smart1010.module_base.room.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

/**
 *  author : Snail
 *  date : 2022/11/19 20:14
 *  description :
 */
class DataViewModel : BaseViewModel() {

    val save = MutableLiveData<Boolean>()
    val users = MutableLiveData<List<User>>()

    /**
     * 保存数据
     */
    fun saveUser(application: Application, name: String, age: Int, sex: String) {
        val time = measureTimeMillis {
            launch({
                val result = withContext(Dispatchers.IO) {
                    AppDatabase.get(application).userDao()
                        .insertUser(User(name = name, age = age, sex = sex))
                }
                save.value = true
            }, {}, {})
        }
    }

    /**
     * 查询数据
     */
    fun getAll(application: Application) {
        launch({
            val result = withContext(Dispatchers.IO) {
                AppDatabase.get(application).userDao().getAllUser()
            }
            users.value = result
        }, {}, {})
    }
}