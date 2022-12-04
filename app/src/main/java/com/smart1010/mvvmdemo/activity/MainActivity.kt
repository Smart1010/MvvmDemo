package com.smart1010.mvvmdemo.activity

import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.hjq.permissions.Permission
import com.smart1010.module_base.Constant
import com.smart1010.module_base.base.BaseActivity
import com.smart1010.module_base.utils.PermissionsUtils
import com.smart1010.mvvmdemo.MessageEvent
import com.smart1010.mvvmdemo.R
import com.smart1010.mvvmdemo.databinding.ActivityMainBinding
import com.smart1010.mvvmdemo.viewModel.MainViewModle
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

        PermissionsUtils.requestPermissions(
            this,
            object : PermissionsUtils.OnPermissionBack {
                override fun onGranted() {
                    Toast.makeText(this@MainActivity, "授权成功", Toast.LENGTH_LONG).show()
                }
            },
            Permission.ACCESS_FINE_LOCATION,
            Permission.ACCESS_COARSE_LOCATION,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.READ_PHONE_STATE,
            Permission.WRITE_SETTINGS,
            Permission.READ_EXTERNAL_STORAGE,
            Permission.RECORD_AUDIO,
            Permission.CAMERA
        )

        Glide
            .with(this)
            .load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg")
            .placeholder(R.mipmap.ic_launcher)
            .override(300, 300)
            .into(mBinding.image1);


        Glide
            .with(this)
            .load("http://guolin.tech/book.png")
            .placeholder(R.mipmap.ic_launcher)
            .into(mBinding.image2);

        /*CoroutineScope(Dispatchers.Main).launch {
           val list=withContext(Dispatchers.IO) {
                 RoomUtils(this@MainActivity.application).db.userDao().getAllUser()
            }
            if (list.isEmpty()){
                Toast.makeText(this@MainActivity.application,"",Toast.LENGTH_LONG).show()
            }
        }*/

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

        fun onDataClick() {
            ARouter.getInstance().build(Constant.data_activity).navigation()
        }

        fun onLoginClick() {
            mViewModel.login("")
        }
    }
}