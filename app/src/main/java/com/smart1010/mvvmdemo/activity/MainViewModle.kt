package com.smart1010.mvvmdemo.activity

import androidx.lifecycle.MutableLiveData
import com.smart1010.module_base.base.BaseViewModel

/**
 *  author : Snail
 *  date : 2022/11/13 20:06
 *  description :
 */
class MainViewModle : BaseViewModel() {

    val name = MutableLiveData<String>().apply {
        this.value = "你好"
    }

}