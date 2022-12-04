package com.smart1010.mvvmdemo.viewModel

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.smart1010.module_base.ApiService
import com.smart1010.module_base.Constant
import com.smart1010.module_base.base.BaseViewModel
import com.smart1010.module_base.utils.RetrofitUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 *  author : Snail
 *  date : 2022/11/13 20:06
 *  description :
 */
class MainViewModle : BaseViewModel() {

    val name = MutableLiveData<String>().apply {
        this.value = "你好"
    }

    /**
     *
     */
    fun login(name: String) {
        val map = HashMap<String, Any>()
        map["usname"] = name
        map["tel"] = ""
        launch({
            val result = withContext(Dispatchers.IO) {
                RetrofitUtils.getInstance().create(ApiService::class.java)
                    .login(mapToRequestBody(map))
            }
            if (result.code == Constant.success_code) {
                //成功
            } else {
                //失败
            }
        }, {
            //异常
        }, {})
    }

    fun mapToRequestBody(map: java.util.HashMap<String, Any>): RequestBody {
        val json = Gson().toJson(map)
//        fileWrite("json===${json}", "info", true)
        return RequestBody.create(MediaType.parse("application/json"), json)
    }

}