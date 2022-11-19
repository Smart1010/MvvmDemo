package com.smart1010.mvvmdemo.activity

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.smart1010.module_base.base.BaseActivity
import com.smart1010.mvvmdemo.Constant
import com.smart1010.mvvmdemo.MessageEvent
import com.smart1010.mvvmdemo.R
import com.smart1010.mvvmdemo.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModle>() {

    private val mPresenter: Presenter by lazy {
        Presenter()
    }

    override fun initLayoutView(): Int {
        return R.layout.activity_main
    }

    override fun initModelClass(): Class<MainViewModle> {
        return MainViewModle::class.java
    }

    override fun initView() {
        EventBus.getDefault().register(this)
        mBinding.viewModel = mViewModel
        mBinding.presenter = mPresenter
//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.IO)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEventBus(message: MessageEvent) {
        mBinding.nameTv.text = message.mes
    }

    inner class Presenter {

        fun onRoutClick(v: View) {
            ARouter.getInstance().build(Constant.arout_activity).navigation()
        }
    }
}