package com.smart1010.module_base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *  author : Snail
 *  date : 2022/11/13 17:26
 *  description : ViewModel基类
 */
abstract class BaseViewModel : ViewModel() {

    val loadState = MutableLiveData<String>()

    /**
     * 调用协程方法
     */
    fun launch(
        block: suspend CoroutineScope.() -> Unit,
        onError: (e: Throwable) -> Unit = {},
        onComplete: () -> Unit
    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
//        Loger.e("错误$throwable\n" + throwable.stackTraceToString(), "error", true)
        }) {
            try {
                block.invoke(this)
            } finally {
                onComplete()
            }
        }
    }
}

