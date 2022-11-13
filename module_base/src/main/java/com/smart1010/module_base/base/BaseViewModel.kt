package com.smart1010.module_base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  author : Snail
 *  date : 2022/11/13 17:26
 *  description : ViewModel基类
 */
open class BaseViewModel : ViewModel() {

    val loadState = MutableLiveData<String>()

}