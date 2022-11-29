package com.smart1010.mvvmdemo.activity

import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.smart1010.module_base.base.BaseActivity
import com.smart1010.module_base.utils.MMKVUtils
import com.smart1010.module_base.Constant
import com.smart1010.mvvmdemo.MessageEvent
import com.smart1010.mvvmdemo.R
import com.smart1010.mvvmdemo.databinding.ActivityAroutBinding
import org.greenrobot.eventbus.EventBus

@Route(path = Constant.arout_activity)
class ARoutActivity : BaseActivity<ActivityAroutBinding, ARoutViewModle>() {

    private val mPresenter: Presenter by lazy {
        Presenter()
    }

    override fun initLayoutView(): Int {
        return R.layout.activity_arout
    }

    override fun initModelClass(): Class<ARoutViewModle> {
        return ARoutViewModle::class.java
    }

    override fun initView() {
        mBinding.presenter = mPresenter
    }

    inner class Presenter {

        fun onBusClick(v: View) {
            EventBus.getDefault().post(MessageEvent("EventBus数据"))
            finish()
        }

        fun onSaveClick() {
            MMKVUtils.put("MMKV_INPUT", mBinding.mmkvEdit.text.toString())
            Toast.makeText(this@ARoutActivity, "保存成功", Toast.LENGTH_LONG).show()
        }

        fun onShowClick() {
            mBinding.mmkvTv.text = MMKVUtils.get("MMKV_INPUT", "") as String
        }


    }
}