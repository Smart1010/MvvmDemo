package com.smart1010.mvvmdemo.activity

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.smart1010.module_base.base.BaseActivity
import com.smart1010.module_base.Constant
import com.smart1010.mvvmdemo.R
import com.smart1010.mvvmdemo.databinding.ActivityDataBinding

@Route(path = Constant.data_activity)
class DataActivity : BaseActivity<ActivityDataBinding, DataViewModel>() {

    override fun initLayoutView(): Int {
        return R.layout.activity_data
    }

    override fun initModelClass(): Class<DataViewModel> {
        return DataViewModel::class.java
    }

    override fun initView() {
        mViewModel.save.observe(this) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show()
        }
        mViewModel.users.observe(this) {
            mBinding.allUser.text = it.toString()
            Toast.makeText(this, "查询数据成功" + it.size, Toast.LENGTH_LONG).show()
        }
        mBinding.saveTx.setOnClickListener {
            mViewModel.saveUser(
                this@DataActivity.application,
                mBinding.nameEdit.text!!.toString().trim(),
                mBinding.ageEdit.text!!.toString().trim().toInt(),
                mBinding.sexEdit.text!!.toString().trim()
            )
        }
        mBinding.allTx.setOnClickListener {
            mViewModel.getAll(this@DataActivity.application)
        }
    }
}


