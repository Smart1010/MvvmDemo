package com.smart1010.mvvmdemo.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.smart1010.module_base.base.BaseActivity
import com.smart1010.mvvmdemo.Constant
import com.smart1010.mvvmdemo.MessageEvent
import com.smart1010.mvvmdemo.R
import com.smart1010.mvvmdemo.databinding.ActivityAroutBinding
import org.greenrobot.eventbus.EventBus

@Route(path = Constant.arout_activity)
class ARoutActivity : BaseActivity<ActivityAroutBinding, ARoutViewModle>() {

    override fun initLayoutView(): Int {
        return R.layout.activity_arout
    }

    override fun initModelClass(): Class<ARoutViewModle> {
        return ARoutViewModle::class.java
    }

    override fun initView() {
        mBinding.testTv.setOnClickListener {
            EventBus.getDefault().post(MessageEvent("EventBus数据"))
            finish()
        }
    }
}