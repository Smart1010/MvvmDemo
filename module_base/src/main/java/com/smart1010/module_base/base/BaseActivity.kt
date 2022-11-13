package com.smart1010.module_base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.smart1010.module_base.utils.ActivityMannagerUtils

/**
 *  author : Snail
 *  date : 2022/11/13 17:36
 *  description :Activity基类
 */
abstract class BaseActivity<T : ViewDataBinding, F : BaseViewModel> : AppCompatActivity() {

    protected lateinit var mViewModel: F
    protected lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMannagerUtils.getInstance().addActivity(this)

        mBinding = DataBindingUtil.setContentView(this, initLayoutView())
        mViewModel = ViewModelProvider(this).get(initModelClass())

        mBinding.lifecycleOwner = this
        initView()
    }

    abstract fun initLayoutView(): Int

    abstract fun initModelClass(): Class<F>

    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        ActivityMannagerUtils.getInstance().finishActivity(this)
    }

}